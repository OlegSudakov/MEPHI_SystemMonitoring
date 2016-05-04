package com.sysmon.core.dal.entity.metric;

import com.sysmon.core.dal.entity.agent.AgentEntity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "COLLECTIBLE_METRIC")
public class CollectibleMetricEntity extends MetricEntity
{
    private static final long serialVersionUID = -7560041285886968324L;

    @ManyToOne
    @JoinColumn(name = "AGENT_ID", referencedColumnName = "ID")
    private AgentEntity agentEntity;

    @Column(name = "PARAMETERS", nullable = false)
    @Lob
    private byte[] parameters;

    public CollectibleMetricEntity() {}

    public CollectibleMetricEntity(MetricTypeEntity metricTypeEntity, String cron, AgentEntity agentEntity, byte[] parameters)
    {
        super(metricTypeEntity, cron);
        this.agentEntity = agentEntity;
        this.parameters = parameters;
    }

    @Override
    public String toString()
    {
        return "CollectibleMetric{" +
                "id=" + id +
                ", metricType=" + metricTypeEntity +
                ", storingPeriod=" + storingPeriod +
                ", cron='" + cron + '\'' +
                ", agent=" + agentEntity +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CollectibleMetricEntity)) return false;
        if (!super.equals(o)) return false;

        CollectibleMetricEntity that = (CollectibleMetricEntity) o;

        if (agentEntity != null ? !agentEntity.equals(that.agentEntity) : that.agentEntity != null) return false;
        return Arrays.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (agentEntity != null ? agentEntity.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(parameters);
        return result;
    }

    public AgentEntity getAgentEntity()
    {
        return agentEntity;
    }

    public void setAgentEntity(AgentEntity agentEntity)
    {
        this.agentEntity = agentEntity;
    }

    public byte[] getParameters()
    {
        return parameters;
    }

    public void setParameters(byte[] parameters)
    {
        this.parameters = parameters;
    }
}
