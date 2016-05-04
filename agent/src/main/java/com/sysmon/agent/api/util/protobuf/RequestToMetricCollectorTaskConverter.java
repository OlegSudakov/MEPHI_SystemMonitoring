package com.sysmon.agent.api.util.protobuf;

import com.sysmon.agent.metriccollector.collectors.api.MetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.command.CommandMetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMetricCollectorTask;
import com.sysmon.common.communication.LinuxAgentProtocol.*;
import org.apache.log4j.Logger;

import java.util.function.Function;

public class RequestToMetricCollectorTaskConverter implements Function<LinuxAgentRequest.Request, MetricCollectorTask>
{
    private static final Logger log = Logger.getLogger(RequestToMetricCollectorTaskConverter.class);

    @Override
    public MetricCollectorTask apply(LinuxAgentRequest.Request request)
    {
        log.debug(String.format("Converting: %s", request));
        if (request.hasCpuInfoRequest()) {
            LinuxAgentRequest.CpuInfoRequest cpuInfoRequest = request.getCpuInfoRequest();
            return new CpuMpstatMetricCollectorTask(
                    cpuInfoRequest.hasUser() && cpuInfoRequest.getUser(),
                    cpuInfoRequest.hasNice() && cpuInfoRequest.getNice(),
                    cpuInfoRequest.hasSys() && cpuInfoRequest.getSys(),
                    cpuInfoRequest.hasIowait() && cpuInfoRequest.getIowait(),
                    cpuInfoRequest.hasIrq() && cpuInfoRequest.getIrq(),
                    cpuInfoRequest.hasSoft() && cpuInfoRequest.getSoft(),
                    cpuInfoRequest.hasSteal() && cpuInfoRequest.getSteal(),
                    cpuInfoRequest.hasGuest() && cpuInfoRequest.getGuest(),
                    cpuInfoRequest.hasGnice() && cpuInfoRequest.getGnice(),
                    cpuInfoRequest.hasIdle() && cpuInfoRequest.getIdle()
            );
        } else if (request.hasMemoryInfoRequest()) {
            LinuxAgentRequest.MemoryInfoRequest memoryInfoRequest = request.getMemoryInfoRequest();
            return new MemoryFreeMetricCollectorTask(
                    memoryInfoRequest.hasTotalMemory() && memoryInfoRequest.getTotalMemory(),
                    memoryInfoRequest.hasFreeMemory() && memoryInfoRequest.getFreeMemory(),
                    memoryInfoRequest.hasTotalSwap() && memoryInfoRequest.getTotalSwap(),
                    memoryInfoRequest.hasFreeSwap() && memoryInfoRequest.getFreeSwap()
            );
        } else if (request.hasCommandExecutionRequest()) {
            LinuxAgentRequest.CommandExecutionRequest commandExecutionRequest = request.getCommandExecutionRequest();
            return new CommandMetricCollectorTask(commandExecutionRequest.getCommand());
        }
        return null;
    }
}