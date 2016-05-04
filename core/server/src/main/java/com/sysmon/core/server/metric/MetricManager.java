package com.sysmon.core.server.metric;

import java.util.Collection;
import java.util.Optional;

public interface MetricManager<T extends Metric>
{
    void add(T metric);

    void remove(T metric);

    void remove(Long id);

    Collection<T> getAll();

    Optional<T> getById(Long id);
}
