package com.sysmon.core.dal.entity.rule;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "FAST_RULE")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class FastRuleEntity extends RuleEntity
{
    private static final long serialVersionUID = 2756498813404802830L;

    @Override
    public String toString()
    {
        return "FastRuleEntity{" +
                "id=" + id +
                ", ruleFormula=" + ruleFormula +
                ", priorityEntity=" + priorityEntity +
                ", reactionTemplateEntityList=" + reactionTemplateEntityList +
                '}';
    }
}
