package com.sysmon.agent.metriccollector.collectors.cpu.mpstat;

public class CpuMpstatMeasureValue
{
    protected final Float user;
    protected final Float nice;
    protected final Float sys;
    protected final Float iowait;
    protected final Float irq;
    protected final Float soft;
    protected final Float steal;
    protected final Float guest;
    protected final Float gnice;
    protected final Float idle;

    public CpuMpstatMeasureValue(
            Float user,
            Float nice,
            Float sys,
            Float iowait,
            Float irq,
            Float soft,
            Float steal,
            Float guest,
            Float gnice,
            Float idle
    )
    {
        this.user = user;
        this.nice = nice;
        this.sys = sys;
        this.iowait = iowait;
        this.irq = irq;
        this.soft = soft;
        this.steal = steal;
        this.guest = guest;
        this.gnice = gnice;
        this.idle = idle;
    }

    @Override
    public String toString()
    {
        return "CpuMpstatMeasureValue{" +
                "user=" + user +
                ", nice=" + nice +
                ", sys=" + sys +
                ", iowait=" + iowait +
                ", irq=" + irq +
                ", soft=" + soft +
                ", steal=" + steal +
                ", guest=" + guest +
                ", gnice=" + gnice +
                ", idle=" + idle +
                '}';
    }

    public Float getUser()
    {
        return user;
    }

    public Float getNice()
    {
        return nice;
    }

    public Float getSys()
    {
        return sys;
    }

    public Float getIowait()
    {
        return iowait;
    }

    public Float getIrq()
    {
        return irq;
    }

    public Float getSoft()
    {
        return soft;
    }

    public Float getSteal()
    {
        return steal;
    }

    public Float getGuest()
    {
        return guest;
    }

    public Float getGnice()
    {
        return gnice;
    }

    public Float getIdle()
    {
        return idle;
    }
}
