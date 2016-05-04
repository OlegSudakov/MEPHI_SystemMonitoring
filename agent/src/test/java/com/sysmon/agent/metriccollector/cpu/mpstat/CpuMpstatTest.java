package com.sysmon.agent.metriccollector.cpu.mpstat;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CpuMpstatMetricMetricCollectorConfigTest.class,
        CpuMpstatMetricCollectorTest.class,
})
public class CpuMpstatTest
{
}
