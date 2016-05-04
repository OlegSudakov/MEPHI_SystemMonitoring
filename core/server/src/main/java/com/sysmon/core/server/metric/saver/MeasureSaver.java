package com.sysmon.core.server.metric.saver;

import com.sysmon.core.server.measure.Measure;

import java.util.Collection;

public interface MeasureSaver
{
    void save(Measure measure);

    void save(Collection<Measure> measures);
}
