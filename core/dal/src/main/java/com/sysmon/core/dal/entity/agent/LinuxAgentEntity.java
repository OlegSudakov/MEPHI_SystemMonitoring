package com.sysmon.core.dal.entity.agent;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "LINUX_AGENT")
@DiscriminatorValue(value = "1")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class LinuxAgentEntity extends AgentEntity
{
    private static final long serialVersionUID = 4118807810187869262L;

    @Column(name = "HOST", nullable = false, length = 128)
    private String host;

    @Column(name = "PORT", nullable = false)
    @Min(1)
    @Max(65535)
    private Integer port;

    @Override
    public String toString()
    {
        return "LinuxAgentEntity{" +
                "id=" + id +
                ", instanceEntity=" + instanceEntity +
                ", collectibleMetricEntityList=" + collectibleMetricEntityList +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }
}
