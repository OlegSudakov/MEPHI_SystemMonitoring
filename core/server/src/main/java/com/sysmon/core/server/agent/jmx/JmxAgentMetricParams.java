package com.sysmon.core.server.agent.jmx;

public class JmxAgentMetricParams
{
    protected final String domainName;
    protected final String objectName;
    protected final String propertyName;

    public JmxAgentMetricParams(
            String domainName,
            String objectName,
            String propertyName
    )
    {
        this.domainName = domainName;
        this.objectName = objectName;
        this.propertyName = propertyName;
    }

    @Override
    public String toString()
    {
        return "JmxagentMetricParams{" +
                "domainName='" + domainName + '\'' +
                ", objectName='" + objectName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                '}';
    }

    public final String getDomainName()
    {
        return domainName;
    }

    public final String getObjectName()
    {
        return objectName;
    }

    public final String getPropertyName()
    {
        return propertyName;
    }
}
