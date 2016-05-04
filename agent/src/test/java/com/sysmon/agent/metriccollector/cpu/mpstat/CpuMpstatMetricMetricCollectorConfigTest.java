package com.sysmon.agent.metriccollector.cpu.mpstat;

import com.sysmon.agent.metriccollector.collectors.cpu.mpstat.CpuMpstatMetricCollectorTask;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.junit.Test;

import static org.junit.Assert.*;

public class CpuMpstatMetricMetricCollectorConfigTest
{
    @Test
    public void createConfigWithEmptyConfiguration()
    {
        Configuration configuration = new BaseConfiguration();
        CpuMpstatMetricCollectorTask config = new CpuMpstatMetricCollectorTask(configuration);
        assertFalse(config.isUserEnabled());
        assertFalse(config.isNiceEnabled());
        assertFalse(config.isSysEnabled());
        assertFalse(config.isIowaitEnabled());
        assertFalse(config.isIrqEnabled());
        assertFalse(config.isSoftEnabled());
        assertFalse(config.isStealEnabled());
        assertFalse(config.isGuestEnabled());
        assertFalse(config.isGniceEnabled());
        assertFalse(config.isIdleEnabled());
    }

    @Test
    public void createConfigWithAllValuesSetToFalse()
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("userEnabled", false);
        configuration.setProperty("niceEnabled", false);
        configuration.setProperty("sysEnabled", false);
        configuration.setProperty("iowaitEnabled", false);
        configuration.setProperty("irqEnabled", false);
        configuration.setProperty("softEnabled", false);
        configuration.setProperty("stealEnabled", false);
        configuration.setProperty("guestEnabled", false);
        configuration.setProperty("gniceEnabled", false);
        configuration.setProperty("idleEnabled", false);

        CpuMpstatMetricCollectorTask config = new CpuMpstatMetricCollectorTask(configuration);
        assertFalse(config.isUserEnabled());
        assertFalse(config.isNiceEnabled());
        assertFalse(config.isSysEnabled());
        assertFalse(config.isIowaitEnabled());
        assertFalse(config.isIrqEnabled());
        assertFalse(config.isSoftEnabled());
        assertFalse(config.isStealEnabled());
        assertFalse(config.isGuestEnabled());
        assertFalse(config.isGniceEnabled());
        assertFalse(config.isIdleEnabled());
    }

    @Test
    public void createConfigWithAllValuesSetToTrueWithMetricsID()
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("userEnabled", true);
        configuration.setProperty("niceEnabled", true);
        configuration.setProperty("sysEnabled", true);
        configuration.setProperty("iowaitEnabled", true);
        configuration.setProperty("irqEnabled", true);
        configuration.setProperty("softEnabled", true);
        configuration.setProperty("stealEnabled", true);
        configuration.setProperty("guestEnabled", true);
        configuration.setProperty("gniceEnabled", true);
        configuration.setProperty("idleEnabled", true);

        CpuMpstatMetricCollectorTask config = new CpuMpstatMetricCollectorTask(configuration);
        assertTrue(config.isUserEnabled());
        assertTrue(config.isNiceEnabled());
        assertTrue(config.isSysEnabled());
        assertTrue(config.isIowaitEnabled());
        assertTrue(config.isIrqEnabled());
        assertTrue(config.isSoftEnabled());
        assertTrue(config.isStealEnabled());
        assertTrue(config.isGuestEnabled());
        assertTrue(config.isGniceEnabled());
        assertTrue(config.isIdleEnabled());
    }
}