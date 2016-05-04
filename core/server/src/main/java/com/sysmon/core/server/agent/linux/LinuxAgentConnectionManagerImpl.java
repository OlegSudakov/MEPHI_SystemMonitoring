package com.sysmon.core.server.agent.linux;

import com.sysmon.core.server.agent.AgentRequest;
import com.sysmon.core.server.agent.AgentResponse;
import com.sysmon.core.server.measure.Measure;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Component
public class LinuxAgentConnectionManagerImpl implements LinuxAgentConnectionManager
{
    private static final Logger log = Logger.getLogger(LinuxAgentConnectionManager.class);

    private final ConcurrentMap<Long, LinuxAgentConnection> linuxAgentConnections = new ConcurrentHashMap<>();

    @Override
    public void add(LinuxAgentConnection agentConnection)
    {
        if (!linuxAgentConnections.containsValue(agentConnection)) {
            linuxAgentConnections.put(agentConnection.getId(), agentConnection);
            agentConnection.start();
        } else {
            throw new IllegalArgumentException("Connection with the same parameters is already configured");
        }
    }

    @Override
    public void remove(LinuxAgentConnection agentConnection)
    {
        if (agentConnection != null) {
            agentConnection.stop();
            remove(agentConnection.getId());
        }
    }

    @Override
    public void remove(Long id)
    {
        linuxAgentConnections.remove(id);
    }

    @Override
    public Collection<LinuxAgentConnection> getAll()
    {
        return linuxAgentConnections.values();
    }

    @Override
    public Optional<LinuxAgentConnection> getById(Long id)
    {
        return Optional.ofNullable(linuxAgentConnections.get(id));
    }

    @PreDestroy
    private void freeResources()
    {
        linuxAgentConnections.values().forEach(LinuxAgentConnection::stop);
    }

    @Override
    protected void finalize() throws Throwable
    {
        freeResources();
        super.finalize();
    }

    @Override
    public CompletableFuture<Optional<AgentResponse>> handle(AgentRequest<LinuxAgentMetric> agentRequest)
    {
        Map<LinuxAgentConnection, List<LinuxAgentMetric>> metricsByConnection = agentRequest.getMetrics().stream()
                .collect(Collectors.groupingBy(linuxAgentMetric -> (LinuxAgentConnection) linuxAgentMetric.getAgentConnection()));
        Collection<CompletableFuture<Optional<AgentResponse>>> completableFutures = new ArrayList<>(metricsByConnection.size());
        metricsByConnection.forEach((conn, list) -> completableFutures.add(conn.handle(new AgentRequest<>(list))));
        return CompletableFuture.supplyAsync(() ->
        {
            Collection<Measure> measures = new ArrayList<>();
            Collection<Optional<AgentResponse>> optionals = completableFutures.stream()
                    .parallel()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
            if (optionals.stream().anyMatch(optional -> !optional.isPresent())) {
                log.error("Cannot proceed agent request because one of he linux agents failed to proceed");
                return Optional.empty();
            } else {
                optionals.stream()
                        .parallel()
                        .map(Optional::get)
                        .map(AgentResponse::getMeasures)
                        .forEach(measures::addAll);
            }
            return Optional.of(new AgentResponse(measures));
        });
    }
}
