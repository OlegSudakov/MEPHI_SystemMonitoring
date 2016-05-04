package com.sysmon.core.server.agent;

import com.sysmon.core.server.agent.jmx.JmxAgentConnection;
import com.sysmon.core.server.agent.jmx.JmxAgentConnectionManager;
import com.sysmon.core.server.agent.jmx.JmxAgentMetric;
import com.sysmon.core.server.agent.linux.LinuxAgentConnection;
import com.sysmon.core.server.agent.linux.LinuxAgentConnectionManager;
import com.sysmon.core.server.agent.linux.LinuxAgentMetric;
import com.sysmon.core.server.measure.Measure;
import com.sysmon.core.server.metric.collectible.CollectibleMetric;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Component
public final class CommonAgentConnectionManagerImpl implements CommonAgentConnectionManager
{
    private static final Logger log = Logger.getLogger(CommonAgentConnectionManagerImpl.class);

    private final ConcurrentMap<AgentConnectionType, Collection<AgentConnection>> agentConnectionByTypeMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<Long, AgentConnection> agentConnectionByIdMap = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock(true);

    private final LinuxAgentConnectionManager linuxAgentConnectionManager;

    private final JmxAgentConnectionManager jmxAgentConnectionManager;

    @Autowired
    public CommonAgentConnectionManagerImpl(
            LinuxAgentConnectionManager linuxAgentConnectionManager,
            JmxAgentConnectionManager jmxAgentConnectionManager
    )
    {
        agentConnectionByTypeMap.put(AgentConnectionType.LINUX, new CopyOnWriteArraySet<>());
        agentConnectionByTypeMap.put(AgentConnectionType.JMX, new CopyOnWriteArraySet<>());
        this.linuxAgentConnectionManager = linuxAgentConnectionManager;
        this.jmxAgentConnectionManager = jmxAgentConnectionManager;
    }

    @Override
    public void add(AgentConnection agentConnection)
    {
        try {
            lock.lock();
            if (agentConnectionByIdMap.containsKey(agentConnection.getId())) {
                throw new IllegalArgumentException(String.format("Agent connection with id %d already exists", agentConnection.getId()));
            }
            agentConnectionByIdMap.put(agentConnection.getId(), agentConnection);
            agentConnectionByTypeMap.get(agentConnection.getAgentConnectionType()).add(agentConnection);
            switch (agentConnection.getAgentConnectionType()) {
                case LINUX:
                    linuxAgentConnectionManager.add((LinuxAgentConnection) agentConnection);
                    break;
                case JMX:
                    jmxAgentConnectionManager.add((JmxAgentConnection) agentConnection);
                    break;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(AgentConnection agentConnection)
    {
        remove(agentConnection.getId());
    }

    @Override
    public void remove(Long id)
    {
        try {
            lock.lock();
            AgentConnection agentConnection = agentConnectionByIdMap.get(id);
            if (agentConnection != null) {
                agentConnectionByIdMap.remove(id);
                agentConnectionByTypeMap.get(agentConnection.getAgentConnectionType()).remove(agentConnection);
                switch (agentConnection.getAgentConnectionType()) {
                    case LINUX:
                        linuxAgentConnectionManager.remove(id);
                        break;
                    case JMX:
                        jmxAgentConnectionManager.remove(id);
                        break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Collection<AgentConnection> getAll()
    {
        return agentConnectionByIdMap.values();
    }

    @Override
    public Optional<AgentConnection> getById(Long id)
    {
        return Optional.ofNullable(agentConnectionByIdMap.get(id));
    }

    @Override
    public Collection<AgentConnection> getAgentConnections(AgentConnectionType agentConnectionType)
    {
        return agentConnectionType != null ? agentConnectionByTypeMap.get(agentConnectionType).stream().collect(Collectors.toSet()) : Collections.emptySet();
    }

    @Override
    public Optional<? extends AgentConnection> getAgentConnection(
            AgentConnectionType agentConnectionType,
            Long id
    )
    {
        switch (agentConnectionType) {
            case LINUX:
                return linuxAgentConnectionManager.getById(id);
            case JMX:
                return jmxAgentConnectionManager.getById(id);
        }
        return Optional.empty();
    }

    @Override
    public CompletableFuture<Optional<AgentResponse>> handle(AgentRequest<CollectibleMetric> agentRequest)
    {
        Collection<LinuxAgentMetric> linuxAgentMetrics = agentRequest.getMetrics().stream()
                .parallel()
                .filter(metric -> metric instanceof LinuxAgentMetric)
                .map(metric -> (LinuxAgentMetric) metric)
                .collect(Collectors.toList()
                );
        CompletableFuture<Optional<AgentResponse>> linuxAgentCF = linuxAgentMetrics.isEmpty() ?
                CompletableFuture.completedFuture(Optional.of(AgentResponse.empty())) :
                linuxAgentConnectionManager.handle(new AgentRequest<>(linuxAgentMetrics));

        Collection<JmxAgentMetric> jmxAgentMetrics = agentRequest.getMetrics().stream()
                .parallel()
                .filter(metric -> metric instanceof JmxAgentMetric)
                .map(metric -> (JmxAgentMetric) metric)
                .collect(Collectors.toList()
                );
        CompletableFuture<Optional<AgentResponse>> jmxAgentCF = jmxAgentMetrics.isEmpty() ?
                CompletableFuture.completedFuture(Optional.of(AgentResponse.empty())) :
                jmxAgentConnectionManager.handle(new AgentRequest<>(jmxAgentMetrics));

        return CompletableFuture.supplyAsync(() ->
        {
            Collection<Measure> measures = new ArrayList<>(agentRequest.getMetrics().size());

            Optional<AgentResponse> linuxAgentResponseOptional = linuxAgentCF.join();
            if (!linuxAgentResponseOptional.isPresent()) {
                log.error("Cannot proceed request because linux agent response failed to proceed");
                return Optional.empty();
            }
            measures.addAll(linuxAgentResponseOptional.get().getMeasures());

            Optional<AgentResponse> jmxAgentResponseOptional = jmxAgentCF.join();
            if (!jmxAgentResponseOptional.isPresent()) {
                log.error("Cannot proceed request because jmx agent response failed to proceed");
                return Optional.empty();
            }
            measures.addAll(jmxAgentResponseOptional.get().getMeasures());

            return Optional.of(new AgentResponse(measures));
        });
    }
}
