package com.sysmon.core.server.agent.linux;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LinuxAgentMetricParamsValidator
{
    static void validate(
            LinuxAgentMetricType linuxAgentMetricType,
            Map<LinuxAgentMetricParam, Object> params
    )
    {
        switch (linuxAgentMetricType) {
            case COMMAND:
                if (params == null || params.get(LinuxAgentMetricParam.COMMAND) == null) {
                    throw new IllegalArgumentException(String.format("Parameter %s for for metric %s should be set", LinuxAgentMetricParam.COMMAND.name(), linuxAgentMetricType.name()));
                }
                break;
        }
    }
}
