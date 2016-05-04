package com.sysmon.core.server.metric.calculable;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CalculableMetricManagerImpl implements CalculableMetricManager
{
    private static final Logger log = Logger.getLogger(CalculableMetricManagerImpl.class);

    private final ConcurrentMap<Long, CalculableMetric> calculableMetricMap = new ConcurrentHashMap<>();

    @Override
    public void add(CalculableMetric metric)
    {
        calculableMetricMap.put(metric.getId(), metric);
    }

    @Override
    public void remove(CalculableMetric metric)
    {
        remove(metric.getId());
    }

    @Override
    public void remove(Long id)
    {
        CalculableMetric calculableMetric = calculableMetricMap.get(id);
        if (calculableMetric != null) {
            calculableMetricMap.remove(id);
        }
    }

    @Override
    public Collection<CalculableMetric> getAll()
    {
        return calculableMetricMap.values();
    }

    @Override
    public Optional<CalculableMetric> getById(Long id)
    {
        return Optional.ofNullable(calculableMetricMap.get(id));
    }
}
