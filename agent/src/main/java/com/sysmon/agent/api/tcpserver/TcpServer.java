package com.sysmon.agent.api.tcpserver;

import com.sysmon.agent.metriccollector.MetricCollectorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class TcpServer
{
    private static final Logger log = Logger.getLogger(TcpServer.class);

    private Set<TcpServerInterfaceConfig> tcpServerInterfaceConfigs = new HashSet<>();
    private Set<TcpServerInterface> tcpServerInterfaces = new HashSet<>();

    @Autowired
    private MetricCollectorService metricCollectorService;

    @Autowired
    public TcpServer(
            @Value("${tcpserver.hosts}") Collection<String> hosts,
            @Value("${tcpserver.ports}") Collection<Integer> ports,
            @Value("${tcpserver.eventLoopThreadsCounts}") Collection<Integer> eventLoopThreadsCounts
    )
    {
        Iterator<String> hostIterator = hosts.iterator();
        Iterator<Integer> portIterator = ports.iterator();
        Iterator<Integer> eventLoopsThreadsCountIterator = eventLoopThreadsCounts.iterator();
        while (hostIterator.hasNext() && portIterator.hasNext() && eventLoopsThreadsCountIterator.hasNext()) {
            String host = hostIterator.next();
            Integer port = portIterator.next();
            Integer maxClientsCount = eventLoopsThreadsCountIterator.next();
            tcpServerInterfaceConfigs.add(new TcpServerInterfaceConfig(host, port, maxClientsCount));
        }
    }

    @PostConstruct
    private void init()
    {
        tcpServerInterfaceConfigs.stream()
                .parallel()
                .map(config -> new TcpServerInterface(config, metricCollectorService))
                .forEach(tcpServerInterfaces::add);
        tcpServerInterfaces.forEach(TcpServerInterface::start);
    }

    @PreDestroy
    private void freeResource()
    {
        tcpServerInterfaces.forEach(TcpServerInterface::stop);
    }

    @Override
    protected void finalize() throws Throwable
    {
        freeResource();
        super.finalize();
    }
}
