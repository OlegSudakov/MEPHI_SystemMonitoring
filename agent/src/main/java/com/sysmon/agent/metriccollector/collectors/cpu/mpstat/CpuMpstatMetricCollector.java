package com.sysmon.agent.metriccollector.collectors.cpu.mpstat;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;
import com.sysmon.agent.metriccollector.collectors.api.MetricCollector;
import com.google.common.primitives.Floats;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class CpuMpstatMetricCollector implements MetricCollector<CpuMpstatMeasureValue>
{
    private static final Logger log = Logger.getLogger(CpuMpstatMetricCollector.class);

    private CpuMpstatMetricCollectorTask cpuMpstatMetricCollectorTask;

    public CpuMpstatMetricCollector(CpuMpstatMetricCollectorTask cpuMpstatMetricCollectorTask)
    {
        this.cpuMpstatMetricCollectorTask = cpuMpstatMetricCollectorTask;
    }

    private CpuMpstatMeasureValue parse(String stringToParse)
    {
        int firstPercentIndex = stringToParse.indexOf("%");
        int cpuIdx = stringToParse.substring(0, firstPercentIndex).lastIndexOf("CPU");
        int headerEndIndex = cpuIdx + stringToParse.substring(cpuIdx).indexOf("\n");
        String headerStr = stringToParse.substring(cpuIdx, headerEndIndex);
        int allIdx = headerEndIndex + stringToParse.substring(headerEndIndex).indexOf("all");
        int infoStrEndIndex = allIdx + stringToParse.substring(allIdx).indexOf("\n");
        String infoStr = stringToParse.substring(allIdx, infoStrEndIndex);
        List<String> headerList = Arrays.asList(headerStr.split("(\\s+)(%*)"));
        List<String> infoList = Arrays.asList(infoStr.split("(\\s+)"));

        Float user = null;
        Float nice = null;
        Float sys = null;
        Float iowait = null;
        Float irq = null;
        Float soft = null;
        Float steal = null;
        Float guest = null;
        Float gnice = null;
        Float idle = null;

        Function<String, Float> valueParser = (metricHeader) -> {
            int indexOfStringToParse = headerList.indexOf(metricHeader);
            if (indexOfStringToParse == -1) {
                log.error(String.format("Cannot find header %s", metricHeader));
                return null;
            }
            String valueStr = infoList.get(indexOfStringToParse).replace(",", ".");
            Float value = Floats.tryParse(valueStr);
            if (value == null) {
                log.error(String.format("%s cannot be parsed for metric type %s", valueStr, metricHeader));
            }
            return value;
        };

        if (cpuMpstatMetricCollectorTask.isUserEnabled()) {
            user = valueParser.apply(MpstatMetricType.USER.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isNiceEnabled()) {
            nice = valueParser.apply(MpstatMetricType.NICE.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isSysEnabled()) {
            sys = valueParser.apply(MpstatMetricType.SYS.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isIowaitEnabled()) {
            iowait = valueParser.apply(MpstatMetricType.IOWAIT.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isIrqEnabled()) {
            irq = valueParser.apply(MpstatMetricType.IRQ.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isSoftEnabled()) {
            soft = valueParser.apply(MpstatMetricType.SOFT.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isStealEnabled()) {
            steal = valueParser.apply(MpstatMetricType.STEAL.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isGuestEnabled()) {
            guest = valueParser.apply(MpstatMetricType.GUEST.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isGniceEnabled()) {
            gnice = valueParser.apply(MpstatMetricType.GNICE.getStringValue());
        }
        if (cpuMpstatMetricCollectorTask.isIdleEnabled()) {
            idle = valueParser.apply(MpstatMetricType.IDLE.getStringValue());
        }

        return new CpuMpstatMeasureValue(user, nice, sys, iowait, irq, soft, steal, guest, gnice, idle);
    }

    @Override
    public CpuMpstatMeasureValue apply(CommandExecutionResult commandExecutionResult)
    {
        return parse(commandExecutionResult.getOutput());
    }

    private enum MpstatMetricType
    {
        USER("usr"),
        NICE("nice"),
        SYS("sys"),
        IOWAIT("iowait"),
        IRQ("irq"),
        SOFT("soft"),
        STEAL("steal"),
        GUEST("guest"),
        GNICE("gnice"),
        IDLE("idle");

        private String stringValue;

        MpstatMetricType(String stringValue)
        {
            this.stringValue = stringValue;
        }

        public String getStringValue()
        {
            return stringValue;
        }
    }
}
