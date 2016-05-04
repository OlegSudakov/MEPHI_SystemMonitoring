package com.sysmon.agent.metriccollector;

import com.sysmon.agent.metriccollector.cpu.mpstat.CpuMpstatTest;
import com.sysmon.agent.metriccollector.memory.free.MemoryFreeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CpuMpstatTest.class,
        MemoryFreeTest.class
})
public class MeasureParserSuite
{
}
