package com.sysmon.core.server.agent.linux;

import com.sysmon.common.communication.LinuxAgentProtocol.LinuxAgentResponse;
import com.sysmon.core.server.agent.AgentRequest;
import com.sysmon.core.server.agent.AgentResponse;
import com.sysmon.core.server.measure.Measure;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.function.BiFunction;

public class LinuxAgentResponseToAgentResponse implements BiFunction<LinuxAgentResponse, AgentRequest<LinuxAgentMetric>, Optional<AgentResponse>>
{
    private static final Logger log = Logger.getLogger(LinuxAgentResponseToAgentResponse.class);

    private Date linuxAgentResponseTimestampToDate(long timestamp)
    {
        return new Date(timestamp * 1000);
    }

    @Override
    public Optional<AgentResponse> apply(
            LinuxAgentResponse linuxAgentResponse,
            AgentRequest<LinuxAgentMetric> linuxAgentMetricAgentRequest
    )
    {
        try {
            Collection<Measure> measures = new ArrayList<>();
            LinuxAgentResponse.CpuInfoResponse cpuInfoResponse = null;
            LinuxAgentResponse.MemoryInfoResponse memoryInfoResponse = null;
            LinuxAgentResponse.CommandExecutionResponse commandExecutionResponse = null;
            for (LinuxAgentResponse.Response response : linuxAgentResponse.getResponsesList()) {
                if (response.hasCpuInfoResponse()) {
                    cpuInfoResponse = response.getCpuInfoResponse();
                } else if (response.hasMemoryInfoResponse()) {
                    memoryInfoResponse = response.getMemoryInfoResponse();
                } else if (response.hasCommandExecutionResponse()) {
                    commandExecutionResponse = response.getCommandExecutionResponse();
                }
            }
            for (LinuxAgentMetric metric : linuxAgentMetricAgentRequest.getMetrics()) {
                switch (metric.getLinuxAgentMetricParams().getLinuxAgentMetricType()) {
                    case CPU_USER:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getUser(), metric));
                        break;
                    case CPU_NICE:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getNice(), metric));
                        break;
                    case CPU_SYS:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getSys(), metric));
                        break;
                    case CPU_IOWAIT:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getIowait(), metric));
                        break;
                    case CPU_IRQ:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getIrq(), metric));
                        break;
                    case CPU_SOFT:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getSoft(), metric));
                        break;
                    case CPU_STEAL:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getSteal(), metric));
                        break;
                    case CPU_GUEST:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getGuest(), metric));
                        break;
                    case CPU_GNICE:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getGnice(), metric));
                        break;
                    case CPU_IDLE:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(cpuInfoResponse.getTimestamp()), cpuInfoResponse.getIdle(), metric));
                        break;
                    case MEMORY_TOTAL_MEMORY:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(memoryInfoResponse.getTimestamp()), memoryInfoResponse.getTotalMemory(), metric));
                        break;
                    case MEMORY_FREE_MEMORY:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(memoryInfoResponse.getTimestamp()), memoryInfoResponse.getFreeMemory(), metric));
                        break;
                    case MEMORY_TOTAL_SWAP:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(memoryInfoResponse.getTimestamp()), memoryInfoResponse.getTotalSwap(), metric));
                        break;
                    case MEMORY_FREE_SWAP:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(memoryInfoResponse.getTimestamp()), memoryInfoResponse.getFreeSwap(), metric));
                        break;
                    case COMMAND:
                        measures.add(new Measure(linuxAgentResponseTimestampToDate(commandExecutionResponse.getTimestamp()), commandExecutionResponse.getExitValue(), metric));
                        break;
                }
            }
            return Optional.of(new AgentResponse(measures));
        } catch (Throwable t) {
            log.error("Cannot convert linux agent response to agent response due to error", t);
            return Optional.empty();
        }
    }
}
