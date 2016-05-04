package com.sysmon.agent.metriccollector.collectors.cpu.mpstat;

import com.sysmon.agent.metriccollector.collectors.api.MetricCollectorTask;
import org.apache.commons.configuration.Configuration;

public class CpuMpstatMetricCollectorTask extends MetricCollectorTask
{

    private final boolean userEnabled;
    private final boolean niceEnabled;
    private final boolean sysEnabled;
    private final boolean iowaitEnabled;
    private final boolean irqEnabled;
    private final boolean softEnabled;
    private final boolean stealEnabled;
    private final boolean guestEnabled;
    private final boolean gniceEnabled;
    private final boolean idleEnabled;

    public CpuMpstatMetricCollectorTask(
            boolean userEnabled,
            boolean niceEnabled,
            boolean sysEnabled,
            boolean iowaitEnabled,
            boolean irqEnabled,
            boolean softEnabled,
            boolean stealEnabled,
            boolean guestEnabled,
            boolean gniceEnabled,
            boolean idleEnabled
    )
    {
        this.userEnabled = userEnabled;
        this.niceEnabled = niceEnabled;
        this.sysEnabled = sysEnabled;
        this.iowaitEnabled = iowaitEnabled;
        this.irqEnabled = irqEnabled;
        this.softEnabled = softEnabled;
        this.stealEnabled = stealEnabled;
        this.guestEnabled = guestEnabled;
        this.gniceEnabled = gniceEnabled;
        this.idleEnabled = idleEnabled;
    }

    public CpuMpstatMetricCollectorTask(Configuration config)
    {
        this(
                config.containsKey("userEnabled") && config.getBoolean("userEnabled"),
                config.containsKey("niceEnabled") && config.getBoolean("niceEnabled"),
                config.containsKey("sysEnabled") && config.getBoolean("sysEnabled"),
                config.containsKey("iowaitEnabled") && config.getBoolean("iowaitEnabled"),
                config.containsKey("irqEnabled") && config.getBoolean("irqEnabled"),
                config.containsKey("softEnabled") && config.getBoolean("softEnabled"),
                config.containsKey("stealEnabled") && config.getBoolean("stealEnabled"),
                config.containsKey("guestEnabled") && config.getBoolean("guestEnabled"),
                config.containsKey("gniceEnabled") && config.getBoolean("gniceEnabled"),
                config.containsKey("idleEnabled") && config.getBoolean("idleEnabled")
        );
    }

    @Override
    public String toString()
    {
        return "CpuMpstatMetricCollectorTask{" +
                "userEnabled=" + userEnabled +
                ", niceEnabled=" + niceEnabled +
                ", sysEnabled=" + sysEnabled +
                ", iowaitEnabled=" + iowaitEnabled +
                ", irqEnabled=" + irqEnabled +
                ", softEnabled=" + softEnabled +
                ", stealEnabled=" + stealEnabled +
                ", guestEnabled=" + guestEnabled +
                ", gniceEnabled=" + gniceEnabled +
                ", idleEnabled=" + idleEnabled +
                '}';
    }

    public boolean isUserEnabled()
    {
        return userEnabled;
    }

    public boolean isNiceEnabled()
    {
        return niceEnabled;
    }

    public boolean isSysEnabled()
    {
        return sysEnabled;
    }

    public boolean isIowaitEnabled()
    {
        return iowaitEnabled;
    }

    public boolean isIrqEnabled()
    {
        return irqEnabled;
    }

    public boolean isSoftEnabled()
    {
        return softEnabled;
    }

    public boolean isStealEnabled()
    {
        return stealEnabled;
    }

    public boolean isGuestEnabled()
    {
        return guestEnabled;
    }

    public boolean isGniceEnabled()
    {
        return gniceEnabled;
    }

    public boolean isIdleEnabled()
    {
        return idleEnabled;
    }
}
