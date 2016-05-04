package com.sysmon.core.server.agent;


import com.sysmon.core.server.measure.Measure;
import com.sysmon.core.server.metric.MeasureResponse;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class AgentResponse extends MeasureResponse
{
    public AgentResponse(Collection<Measure> measures)
    {
        super(measures);
    }

    public static AgentResponse empty()
    {
        return new AgentResponse(Collections.emptySet());
    }

    @Override
    public String toString()
    {
        return "AgentResponse{" +
                "measures=[" + measures.stream().parallel().map(Object::toString).collect(Collectors.joining(", ")) + "]" +
                '}';
    }
}
