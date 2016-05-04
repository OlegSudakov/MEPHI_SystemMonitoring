package com.sysmon.core.server.metric;

import com.sysmon.core.server.agent.AgentRequest;
import com.sysmon.core.server.agent.AgentResponse;
import com.sysmon.core.server.agent.CommonAgentConnectionManager;
import com.sysmon.core.server.measure.Measure;
import com.sysmon.core.server.metric.calculable.CalculableMetric;
import com.sysmon.core.server.metric.calculable.CalculableMetricManager;
import com.sysmon.core.server.metric.collectible.CollectibleMetric;
import com.sysmon.core.server.metric.collectible.CollectibleMetricManager;
import com.sysmon.core.server.metric.saver.MetricSaver;
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
import java.util.stream.Collectors;

@Component
public final class CommonMetricManagerImpl implements CommonMetricManager
{
    private static final Logger log = Logger.getLogger(CommonMetricManagerImpl.class);

    private final ConcurrentMap<MetricTypeEnum, Collection<Metric>> metricByTypeMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<Long, Metric> metricByIdMap = new ConcurrentHashMap<>();

    private final MetricSaver metricSaver;
    private final CommonAgentConnectionManager commonAgentConnectionManager;
    private final CollectibleMetricManager collectibleMetricManager;
    private final CalculableMetricManager calculableMetricManager;

    @Autowired
    public CommonMetricManagerImpl(
            MetricSaver metricSaver,
            CommonAgentConnectionManager commonAgentConnectionManager,
            CollectibleMetricManager collectibleMetricManager,
            CalculableMetricManager calculableMetricManager
    )
    {
        metricByTypeMap.put(MetricTypeEnum.COLLECTIBLE, new CopyOnWriteArraySet<>());
        metricByTypeMap.put(MetricTypeEnum.CALCULABLE, new CopyOnWriteArraySet<>());
        this.metricSaver = metricSaver;
        this.commonAgentConnectionManager = commonAgentConnectionManager;
        this.collectibleMetricManager = collectibleMetricManager;
        this.calculableMetricManager = calculableMetricManager;
    }

    @Override
    public void add(Metric metric)
    {
        if (metricByIdMap.containsKey(metric.getId())) {
            throw new IllegalArgumentException(String.format("Metric with id %d already exists", metric.getId()));
        }
        metricByIdMap.put(metric.getId(), metric);
        metricByTypeMap.get(metric.getMetricTypeEnum()).add(metric);
        switch (metric.getMetricTypeEnum()) {
            case COLLECTIBLE:
                collectibleMetricManager.add((CollectibleMetric) metric);
                break;
            case CALCULABLE:
                collectibleMetricManager.add((CollectibleMetric) metric);
                break;
        }
        metricSaver.add(metric);
    }

    @Override
    public void remove(Metric metric)
    {
        remove(metric.getId());
    }

    @Override
    public void remove(Long id)
    {
        Metric metric = metricByIdMap.get(id);
        if (metric != null) {
            metricByIdMap.remove(id);
            metricByTypeMap.get(metric.getMetricTypeEnum()).remove(metric);
            switch (metric.getMetricTypeEnum()) {
                case COLLECTIBLE:
                    collectibleMetricManager.remove((CollectibleMetric) metric);
                    break;
                case CALCULABLE:
                    calculableMetricManager.remove((CalculableMetric) metric);
                    break;
            }
            metricSaver.remove(metric);
        }
    }

    @Override
    public Collection<Metric> getAll()
    {
        return metricByIdMap.values();
    }

    @Override
    public Collection<Metric> getMetrics(MetricTypeEnum metricTypeEnum)
    {
        return metricTypeEnum != null ? metricByTypeMap.get(metricTypeEnum).stream().collect(Collectors.toSet()) : Collections.emptySet();
    }

    @Override
    public Optional<Metric> getById(Long id)
    {
        return Optional.ofNullable(metricByIdMap.get(id));
    }

    @Override
    public Optional<? extends Metric> getMetrics(
            MetricTypeEnum metricTypeEnum,
            Long id
    )
    {
        switch (metricTypeEnum) {
            case COLLECTIBLE:
                return collectibleMetricManager.getById(id);
            case CALCULABLE:
                return calculableMetricManager.getById(id);
        }
        return Optional.empty();
    }

    @Override
    public CompletableFuture<Optional<MeasureResponse>> handle(MetricRequest<Metric> metrics)
    {
        return CompletableFuture.supplyAsync(() -> {
            Collection<Measure> measures = new ArrayList<>();

            Collection<CollectibleMetric> collectibleMetrics = metrics.getMetrics().stream()
                    .parallel()
                    .filter(metric -> metric instanceof CollectibleMetric)
                    .map(metric -> (CollectibleMetric) metric)
                    .collect(Collectors.toList());

            CompletableFuture<Optional<AgentResponse>> collectibleMetricCompletableFuture = commonAgentConnectionManager.handle(new AgentRequest<>(collectibleMetrics));
            Optional<AgentResponse> agentResponseOptional = collectibleMetricCompletableFuture.join();
            if (!agentResponseOptional.isPresent()) {
                log.error("Cannot proceed metric response because collectible metrics have not been recieved");
                return Optional.empty();
            }
            measures.addAll(agentResponseOptional.get().getMeasures());
            return Optional.of(new MeasureResponse(measures));
        });
    }

//    @PostConstruct
//    public void init()
//    {
//        LinuxAgentConnection linuxAgentConnection = new LinuxAgentConnection(0L, "0.0.0.0", 8130);
//        commonAgentConnectionManager.add(linuxAgentConnection);
//        CompletableFuture
//                .runAsync(() -> {
//                    try {
//                        Thread.sleep(1000);
//                        for (int i = 0; i < 2; ++i) {
//                            boolean even = i % 2 == 0;
//                            boolean odd = !even;
//                            Collection<Metric> linuxAgentMetrics = new ArrayList<>();
//                            MetricType metricType = new MetricType(0L, "Default metric type", DataType.FLOAT);
//                            if (even) {
//                                linuxAgentMetrics.add(new LinuxAgentMetric(0L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_USER, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(2L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_SYS, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(4L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_IRQ, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(6L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_STEAL, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(8L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_GUEST, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(10L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.MEMORY_TOTAL_MEMORY, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(12L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.MEMORY_TOTAL_SWAP, null));
//                            } else if (odd) {
//                                linuxAgentMetrics.add(new LinuxAgentMetric(1L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_NICE, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(3L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_IOWAIT, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(5L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_SOFT, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(7L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_GNICE, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(9L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.CPU_IDLE, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(11L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.MEMORY_FREE_MEMORY, null));
//                                linuxAgentMetrics.add(new LinuxAgentMetric(13L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.MEMORY_FREE_SWAP, null));
//                            }
//                            {
//                                Map<LinuxAgentMetricParam, Object> params = new HashMap<>(1);
//                                params.put(LinuxAgentMetricParam.COMMAND, "abcdefg");
//                                linuxAgentMetrics.add(new LinuxAgentMetric(14L, metricType, 0L, "", linuxAgentConnection, LinuxAgentMetricType.COMMAND, params));
//                            }
//                            CompletableFuture<Optional<MeasureResponse>> measureResponseOptionalCompletableFuture = handle(new MetricRequest<>(linuxAgentMetrics));
//                            int finalI = i;
//                            CompletableFuture
//                                    .runAsync(() -> {
//                                        Optional<MeasureResponse> measureResponseOptional = measureResponseOptionalCompletableFuture.join();
//                                        if (measureResponseOptional.isPresent()) {
//                                            MeasureResponse measureResponse = measureResponseOptional.get();
//                                            log.debug(String.format("Got measures: %s", measureResponse.getMeasures().stream().parallel()
//                                                    .map(Object::toString)
//                                                    .collect(Collectors.joining(", "))));
//                                        }
//                                    })
//                                    .exceptionally(t -> {
//                                        log.error(String.format("Cannot perform request for response with id %d", finalI), t);
//                                        return null;
//                                    });
//                        }
//                    } catch (InterruptedException e) {
//                        log.error(e);
//                    }
//                })
//                .exceptionally(t -> {
//                    log.error("Error on initialization", t);
//                    return null;
//                });
//    }
}
