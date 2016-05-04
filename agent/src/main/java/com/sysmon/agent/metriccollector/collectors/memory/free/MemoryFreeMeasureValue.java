package com.sysmon.agent.metriccollector.collectors.memory.free;

public class MemoryFreeMeasureValue
{
    protected Long totalMemory;
    protected Long freeMemory;
    protected Long totalSwap;
    protected Long freeSwap;


    public MemoryFreeMeasureValue(
            Long totalMemory,
            Long freeMemory,
            Long totalSwap,
            Long freeSwap
    )
    {
        this.totalMemory = totalMemory;
        this.freeMemory = freeMemory;
        this.totalSwap = totalSwap;
        this.freeSwap = freeSwap;
    }

    @Override
    public String toString()
    {
        return "MemoryFreeMeasureValue{" +
                "totalMemory=" + totalMemory +
                ", freeMemory=" + freeMemory +
                ", totalSwap=" + totalSwap +
                ", freeSwap=" + freeSwap +
                '}';
    }

    public Long getTotalMemory()
    {
        return totalMemory;
    }

    public Long getFreeMemory()
    {
        return freeMemory;
    }

    public Long getTotalSwap()
    {
        return totalSwap;
    }

    public Long getFreeSwap()
    {
        return freeSwap;
    }
}
