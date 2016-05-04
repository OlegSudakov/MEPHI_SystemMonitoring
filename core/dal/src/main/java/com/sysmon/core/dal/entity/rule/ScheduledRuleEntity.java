package com.sysmon.core.dal.entity.rule;

import javax.persistence.*;

@Entity
@Table(name = "SCHEDULED_RULE")
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class ScheduledRuleEntity extends RuleEntity
{
    private static final long serialVersionUID = 2269352368850678554L;

    @Column(name = "CRON", nullable = false, length = 256)
    private String cron;

    @Override
    public String toString()
    {
        return "ScheduledRuleEntity{" +
                "id=" + id +
                ", ruleFormula=" + ruleFormula +
                ", priorityEntity=" + priorityEntity +
                ", reactionTemplateEntityList=" + reactionTemplateEntityList +
                ", cron='" + cron + '\'' +
                '}';
    }

    public String getCron()
    {
        return cron;
    }

    public void setCron(String cron)
    {
        this.cron = cron;
    }
}
