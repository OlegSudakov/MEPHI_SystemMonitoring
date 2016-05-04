package com.sysmon.agent.metriccollector.memory.free;

import com.sysmon.agent.metriccollector.collectors.memory.free.MemoryFreeMetricCollectorTask;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryFreeMetricCollectorTaskTest
{
    @Test
    public void createConfigWithEmptyConfiguration()
    {
        Configuration configuration = new BaseConfiguration();
        MemoryFreeMetricCollectorTask config = new MemoryFreeMetricCollectorTask(configuration);
        assertFalse(config.isFreeMemoryEnabled());
        assertFalse(config.isTotalMemoryEnabled());
        assertFalse(config.isFreeSwapEnabled());
        assertFalse(config.isTotalSwapEnabled());
    }

    @Test
    public void createConfigWithAllValuesSetToFalse()
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("freeMemoryEnabled", false);
        configuration.setProperty("totalMemoryEnabled", false);
        configuration.setProperty("freeSwapEnabled", false);
        configuration.setProperty("totalSwapEnabled", false);

        MemoryFreeMetricCollectorTask config = new MemoryFreeMetricCollectorTask(configuration);
        assertFalse(config.isFreeMemoryEnabled());
        assertFalse(config.isTotalMemoryEnabled());
        assertFalse(config.isFreeSwapEnabled());
        assertFalse(config.isTotalSwapEnabled());
    }

    @Test
    public void createConfigWithAllValuesSetToTrueWithMetricsID()
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("freeMemoryEnabled", true);
        configuration.setProperty("totalMemoryEnabled", true);
        configuration.setProperty("freeSwapEnabled", true);
        configuration.setProperty("totalSwapEnabled", true);

        MemoryFreeMetricCollectorTask config = new MemoryFreeMetricCollectorTask(configuration);
        assertTrue(config.isFreeMemoryEnabled());
        assertTrue(config.isTotalMemoryEnabled());
        assertTrue(config.isFreeSwapEnabled());
        assertTrue(config.isTotalSwapEnabled());
    }
}