package com.sysmon.agent.api.util.protobuf;

import com.sysmon.agent.measure.Measure;
import com.sysmon.agent.metriccollector.collectors.command.CommandMeasure;
import com.sysmon.agent.metriccollector.collectors.command.CommandMeasureValue;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMeasure;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMeasureValue;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMeasure;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMeasureValue;
import com.sysmon.common.communication.LinuxAgentProtocol.LinuxAgentResponse.*;

import java.util.function.Function;

public class MeasureToResponse implements Function<Measure, Response>
{
    @Override
    public Response apply(Measure measure)
    {
        Response.Builder responseBuilder = Response.newBuilder();
        if (measure instanceof CpuMpstatMeasure) {
            CpuMpstatMeasure cpuMpstatMeasure = (CpuMpstatMeasure) measure;
            CpuMpstatMeasureValue cpuMpstatMeasureValue = cpuMpstatMeasure.getValue();
            CpuInfoResponse.Builder builder = CpuInfoResponse.newBuilder().setTimestamp(cpuMpstatMeasure.getTimestamp());
            if (cpuMpstatMeasureValue.getUser() != null) {
                builder.setUser(cpuMpstatMeasureValue.getUser());
            }
            if (cpuMpstatMeasureValue.getNice() != null) {
                builder.setNice(cpuMpstatMeasureValue.getNice());
            }
            if (cpuMpstatMeasureValue.getSys() != null) {
                builder.setSys(cpuMpstatMeasureValue.getSys());
            }
            if (cpuMpstatMeasureValue.getIowait() != null) {
                builder.setIowait(cpuMpstatMeasureValue.getIowait());
            }
            if (cpuMpstatMeasureValue.getIrq() != null) {
                builder.setIrq(cpuMpstatMeasureValue.getIrq());
            }
            if (cpuMpstatMeasureValue.getSoft() != null) {
                builder.setSoft(cpuMpstatMeasureValue.getSoft());
            }
            if (cpuMpstatMeasureValue.getSteal() != null) {
                builder.setSteal(cpuMpstatMeasureValue.getSteal());
            }
            if (cpuMpstatMeasureValue.getGuest() != null) {
                builder.setGuest(cpuMpstatMeasureValue.getGuest());
            }
            if (cpuMpstatMeasureValue.getGnice() != null) {
                builder.setGnice(cpuMpstatMeasureValue.getGnice());
            }
            if (cpuMpstatMeasureValue.getIdle() != null) {
                builder.setIdle(cpuMpstatMeasureValue.getIdle());
            }
            responseBuilder.setCpuInfoResponse(builder);
        } else if (measure instanceof MemoryFreeMeasure) {
            MemoryFreeMeasure memoryFreeMeasure = (MemoryFreeMeasure) measure;
            MemoryFreeMeasureValue memoryFreeMeasureValue = memoryFreeMeasure.getValue();
            MemoryInfoResponse.Builder builder = MemoryInfoResponse.newBuilder().setTimestamp(memoryFreeMeasure.getTimestamp());
            if (memoryFreeMeasureValue.getTotalMemory() != null) {
                builder.setTotalMemory(memoryFreeMeasureValue.getTotalMemory());
            }
            if (memoryFreeMeasureValue.getFreeMemory() != null) {
                builder.setFreeMemory(memoryFreeMeasureValue.getFreeMemory());
            }
            if (memoryFreeMeasureValue.getTotalSwap() != null) {
                builder.setTotalSwap(memoryFreeMeasureValue.getTotalSwap());
            }
            if (memoryFreeMeasureValue.getFreeSwap() != null) {
                builder.setFreeSwap(memoryFreeMeasureValue.getFreeSwap());
            }
            responseBuilder.setMemoryInfoResponse(builder);
        } else if (measure instanceof CommandMeasure) {
            CommandMeasure commandMeasure = (CommandMeasure) measure;
            CommandMeasureValue commandMeasureValue = ((CommandMeasure) measure).getValue();
            CommandExecutionResponse.Builder builder = CommandExecutionResponse.newBuilder()
                    .setTimestamp(commandMeasure.getTimestamp())
                    .setExitValue(commandMeasureValue.getExitValue())
                    .setOutput(commandMeasureValue.getOutput());
            responseBuilder.setCommandExecutionResponse(builder);
        }
        return responseBuilder.build();
    }
}