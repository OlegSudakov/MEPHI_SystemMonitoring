package com.sysmon.core.server.metric.collectible;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CollectibleMetricManagerImpl implements CollectibleMetricManager
{
    private static final Logger log = Logger.getLogger(CollectibleMetricManagerImpl.class);

    private final ConcurrentMap<Long, CollectibleMetric> collectibleMetricMap = new ConcurrentHashMap<>();

    @Override
    public void add(CollectibleMetric metric)
    {
        collectibleMetricMap.put(metric.getId(), metric);
    }

    @Override
    public void remove(CollectibleMetric metric)
    {
        collectibleMetricMap.remove(metric.getId());
    }

    @Override
    public void remove(Long id)
    {
        collectibleMetricMap.remove(id);
    }

    @Override
    public Collection<CollectibleMetric> getAll()
    {
        return collectibleMetricMap.values();
    }

    @Override
    public Optional<CollectibleMetric> getById(Long id)
    {
        return Optional.ofNullable(collectibleMetricMap.get(id));
    }
}
