package com.sysmon.core.server.metric;

import com.sysmon.core.server.measure.Measure;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Collections;

public class MeasureResponse
{
    protected final ImmutableSet<Measure> measures;

    public MeasureResponse(Collection<Measure> measures)
    {
        this.measures = ImmutableSet.copyOf(measures);
    }

    public static MeasureResponse empty()
    {
        return new MeasureResponse(Collections.emptySet());
    }

    public Collection<Measure> getMeasures()
    {
        return measures;
    }
}
