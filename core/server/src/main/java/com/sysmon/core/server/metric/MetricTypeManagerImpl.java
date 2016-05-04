package com.sysmon.core.server.metric;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MetricTypeManagerImpl implements MetricTypeManager
{
    private final ConcurrentMap<Long, MetricType> metricTypeById = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();

    @Override
    public Optional<MetricType> getById(Long id)
    {
        return Optional.ofNullable(metricTypeById.get(id));
    }

    @Override
    public void add(MetricType metricType)
    {
        try {
            lock.lock();
            if (metricTypeById.containsKey(metricType.getId())) {
                throw new IllegalArgumentException(String.format("Metric with id %d already exists", metricType.getId()));
            }
            metricTypeById.put(metricType.getId(), metricType);
        }
        finally {
            lock.unlock();
        }
    }
}
