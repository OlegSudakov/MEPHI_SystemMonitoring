package com.sysmon.agent.metriccollector.cpu.mpstat;

import com.sysmon.agent.metriccollector.Util;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMeasureValue;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMetricCollector;
import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMetricCollectorTask;
import org.junit.Test;

import static org.junit.Assert.*;

public class CpuMpstatMetricCollectorTest
{
    private Util util = new Util();

    @Test(expected = Throwable.class)
    public void parseNullString() throws Exception
    {
        CpuMpstatMetricCollectorTask task = new CpuMpstatMetricCollectorTask(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );
        new CpuMpstatMetricCollector(task).apply(null);
    }

    @Test(expected = Throwable.class)
    public void parseEmptyString() throws Exception
    {
        CpuMpstatMetricCollectorTask task = new CpuMpstatMetricCollectorTask(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );
        new CpuMpstatMetricCollector(task).apply(util.constructCommandExecutionResult(""));
    }

    @Test(expected = Throwable.class)
    public void parseSomeString()
    {
        CpuMpstatMetricCollectorTask task = new CpuMpstatMetricCollectorTask(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );
        new CpuMpstatMetricCollector(task).apply(util.constructCommandExecutionResult("some str"));
    }

    @Test
    public void parseWithAllPropertiesSet()
    {
        CpuMpstatMetricCollectorTask task = new CpuMpstatMetricCollectorTask(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );
        String stringToParse = "Linux 3.19.0-42-generic (ubuntu) \t01/18/2016 \t_x86_64_\t(2 CPU)\n\n" +
                "10:53:39 AM  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle\n" +
                "10:53:39 AM  all    0.78    0.05    0.98    0.02    0.23    0.48    0.32    0.79    0.62   95.16\n";
        CpuMpstatMeasureValue bareMeasure = new CpuMpstatMetricCollector(task).apply(util.constructCommandExecutionResult(stringToParse));
        assertEquals(bareMeasure.getUser(), 0.78, .00005);
        assertEquals(bareMeasure.getNice(), 0.05D, 0.00005);
        assertEquals(bareMeasure.getSys(), 0.98D, 0.00005);
        assertEquals(bareMeasure.getIowait(), 0.02D, 0.00005);
        assertEquals(bareMeasure.getIrq(),0.23D, 0.00005);
        assertEquals(bareMeasure.getSoft(), 0.48D, 0.00005);
        assertEquals(bareMeasure.getSteal(), 0.32D, 0.00005);
        assertEquals(bareMeasure.getGuest(), 0.79D, 0.00005);
        assertEquals(bareMeasure.getGnice(), 0.62D, 0.00005);
        assertEquals(bareMeasure.getIdle(), 95.16D, 0.00005);
    }

    @Test
    public void parseWithFourPropertiesSet()
    {
        CpuMpstatMetricCollectorTask task = new CpuMpstatMetricCollectorTask(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );
        String stringToParse = "Linux 3.19.0-42-generic (ubuntu) \t01/18/2016 \t_x86_64_\t(2 CPU)\n\n" +
                "10:53:39 AM  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle\n" +
                "10:53:39 AM  all    0.12    45.67    17.98    28.02    1.07    17.28   13.24    97.79    45.62   0.16\n";
        CpuMpstatMeasureValue bareMeasure = new CpuMpstatMetricCollector(task).apply(util.constructCommandExecutionResult(stringToParse));
        assertEquals(bareMeasure.getUser(), 0.12D, 0.00005);
        assertEquals(bareMeasure.getNice(), 45.67D, 0.00005);
        assertEquals(bareMeasure.getSys(), 17.98D, 0.00005);
        assertEquals(bareMeasure.getIowait(), 28.02D, 0.00005);
        assertEquals(bareMeasure.getIrq(), 1.07D, 0.00005);
        assertEquals(bareMeasure.getSoft(), 17.28D, 0.00005);
        assertEquals(bareMeasure.getSteal(), 13.24D, 0.00005);
        assertEquals(bareMeasure.getGuest(), 97.79D, 0.00005);
        assertEquals(bareMeasure.getGnice(), 45.62D, 0.00005);
        assertEquals(bareMeasure.getIdle(), 0.16D, 0.00005);
    }
}