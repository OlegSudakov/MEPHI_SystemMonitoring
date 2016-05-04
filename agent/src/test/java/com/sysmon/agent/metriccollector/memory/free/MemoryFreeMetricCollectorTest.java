package com.sysmon.agent.metriccollector.memory.free;

import com.sysmon.agent.metriccollector.Util;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMeasureValue;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMetricCollector;
import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMetricCollectorTask;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryFreeMetricCollectorTest
{
    private Util util = new Util();
    
    @Test(expected = Throwable.class)
    public void parseWithNullString() throws Exception
    {
        MemoryFreeMetricCollectorTask task = new MemoryFreeMetricCollectorTask(
                true,
                true,
                true,
                true
        );
        new MemoryFreeMetricCollector(task).apply(null);
    }

    @Test(expected = Throwable.class)
    public void parseWithEmptyString() throws Exception
    {
        MemoryFreeMetricCollectorTask task = new MemoryFreeMetricCollectorTask(
                true,
                true,
                true,
                true
        );
        new MemoryFreeMetricCollector(task).apply(util.constructCommandExecutionResult(""));
    }

    @Test(expected = Throwable.class)
    public void parseWithSomeString()
    {
        MemoryFreeMetricCollectorTask task = new MemoryFreeMetricCollectorTask(
                true,
                true,
                true,
                true
        );
        new MemoryFreeMetricCollector(task).apply(util.constructCommandExecutionResult("some str"));
    }

    @Test
    public void parseFirstCase()
    {
        MemoryFreeMetricCollectorTask task = new MemoryFreeMetricCollectorTask(
                true,
                true,
                true,
                true
        );
        String stringToParse = "  total       used       free     shared    buffers     cached\n" +
                    "Mem:          3936       2100       1835         31        116        735\n" +
                    "-/+ buffers/cache:       1249       2686\n" +
                    "Swap:         4093        108       3985\n";
        MemoryFreeMeasureValue bareMeasure = new MemoryFreeMetricCollector(task).apply(util.constructCommandExecutionResult(stringToParse));
        assertEquals(bareMeasure.getFreeMemory().longValue(), 1835L);
        assertEquals(bareMeasure.getTotalMemory().longValue(), 3936L);
        assertEquals(bareMeasure.getFreeSwap().longValue(), 3985L);
        assertEquals(bareMeasure.getTotalSwap().longValue(), 4093L);
    }

    @Test
    public void parseSecondCase()
    {
        MemoryFreeMetricCollectorTask task = new MemoryFreeMetricCollectorTask(
                true,
                true,
                true,
                true
        );
        String stringToParse = "  total       used       free     shared    buffers     cached\n" +
                    "Mem:          8765       6541       2048         31        116        735\n" +
                    "-/+ buffers/cache:       4562       2686\n" +
                    "Swap:         9876       1024       8794\n";
        MemoryFreeMeasureValue bareMeasure = new MemoryFreeMetricCollector(task).apply(util.constructCommandExecutionResult(stringToParse));
        assertEquals(bareMeasure.getFreeMemory().longValue(), 2048);
        assertEquals(bareMeasure.getTotalMemory().longValue(), 8765L);
        assertEquals(bareMeasure.getFreeSwap().longValue(), 8794L);
        assertEquals(bareMeasure.getTotalSwap().longValue(), 9876L);
    }
}