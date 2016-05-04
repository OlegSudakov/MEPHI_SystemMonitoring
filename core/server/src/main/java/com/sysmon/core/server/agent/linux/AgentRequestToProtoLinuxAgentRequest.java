package com.sysmon.core.server.agent.linux;

import com.sysmon.common.communication.LinuxAgentProtocol.LinuxAgentRequest;
import com.sysmon.core.server.agent.AgentRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

public class AgentRequestToProtoLinuxAgentRequest implements BiFunction<AgentRequest<LinuxAgentMetric>, Long, LinuxAgentRequest>
{
    @Override
    public LinuxAgentRequest apply(
            AgentRequest<LinuxAgentMetric> linuxAgentMetricAgentRequest,
            Long id
    )
    {
        LinuxAgentRequest.Builder linuxAgentRequestBuilder = LinuxAgentRequest.newBuilder();

        LinuxAgentRequest.Request.Builder cpuRequestBuilder = LinuxAgentRequest.Request.newBuilder();
        LinuxAgentRequest.CpuInfoRequest.Builder cpuInfoRequestBuilder = LinuxAgentRequest.CpuInfoRequest.newBuilder();
        boolean cpuInfoRequestIsSet = false;

        LinuxAgentRequest.Request.Builder memoryRequestBuilder = LinuxAgentRequest.Request.newBuilder();
        LinuxAgentRequest.MemoryInfoRequest.Builder memoryInfoRequestBuilder = LinuxAgentRequest.MemoryInfoRequest.newBuilder();
        boolean memoryInfoRequestIsSet = false;

        LinuxAgentRequest.Request.Builder commandExecutionRequestBuilder = LinuxAgentRequest.Request.newBuilder();
        LinuxAgentRequest.CommandExecutionRequest.Builder commandExecutionInfoRequestBuilder = LinuxAgentRequest.CommandExecutionRequest.newBuilder();
        boolean commandExecutionInfoRequestIsSet = false;

        for (LinuxAgentMetric metric : linuxAgentMetricAgentRequest.getMetrics())
        {
            switch (metric.getLinuxAgentMetricParams().getLinuxAgentMetricType()) {
                case CPU_USER:
                    cpuInfoRequestBuilder.setUser(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_NICE:
                    cpuInfoRequestBuilder.setNice(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_SYS:
                    cpuInfoRequestBuilder.setSys(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_IOWAIT:
                    cpuInfoRequestBuilder.setIowait(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_IRQ:
                    cpuInfoRequestBuilder.setIrq(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_SOFT:
                    cpuInfoRequestBuilder.setSoft(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_STEAL:
                    cpuInfoRequestBuilder.setSteal(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_GUEST:
                    cpuInfoRequestBuilder.setGuest(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_GNICE:
                    cpuInfoRequestBuilder.setGnice(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case CPU_IDLE:
                    cpuInfoRequestBuilder.setIdle(true);
                    cpuInfoRequestIsSet = true;
                    break;
                case MEMORY_TOTAL_MEMORY:
                    memoryInfoRequestBuilder.setTotalMemory(true);
                    memoryInfoRequestIsSet = true;
                    break;
                case MEMORY_FREE_MEMORY:
                    memoryInfoRequestBuilder.setFreeMemory(true);
                    memoryInfoRequestIsSet = true;
                    break;
                case MEMORY_TOTAL_SWAP:
                    memoryInfoRequestBuilder.setTotalSwap(true);
                    memoryInfoRequestIsSet = true;
                    break;
                case MEMORY_FREE_SWAP:
                    memoryInfoRequestBuilder.setFreeSwap(true);
                    memoryInfoRequestIsSet = true;
                    break;
                case COMMAND:
                    commandExecutionInfoRequestBuilder.setCommand((String) metric.getLinuxAgentMetricParams().getParams().get(LinuxAgentMetricParam.COMMAND));
                    commandExecutionInfoRequestIsSet = true;
                    break;
            }
        }

        Collection<LinuxAgentRequest.Request> requests = new ArrayList<>(3);

        if (cpuInfoRequestIsSet) {
            cpuRequestBuilder.setCpuInfoRequest(cpuInfoRequestBuilder);
            requests.add(cpuRequestBuilder.build());
        }

        if (memoryInfoRequestIsSet) {
            memoryRequestBuilder.setMemoryInfoRequest(memoryInfoRequestBuilder);
            requests.add(memoryRequestBuilder.build());
        }

        if (commandExecutionInfoRequestIsSet) {
            commandExecutionRequestBuilder.setCommandExecutionRequest(commandExecutionInfoRequestBuilder);
            requests.add(commandExecutionRequestBuilder.build());
        }
        linuxAgentRequestBuilder.addAllRequests(requests);

        linuxAgentRequestBuilder.setId(id);
        return linuxAgentRequestBuilder.build();
    }
}
