package com.sysmon.agent.metriccollector.memory.free;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MemoryFreeMetricCollectorTaskTest.class,
        MemoryFreeMetricCollectorTest.class,
})
public class MemoryFreeTest
{
}
