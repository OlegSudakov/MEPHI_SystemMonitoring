package com.sysmon.core.dal.entity.formula;

import com.sysmon.core.dal.entity.rule.RuleEntity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "RULE_FORMULA")
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class RuleFormulaEntity extends FormulaEntity
{
    private static final long serialVersionUID = -4721029984628657889L;

    @OneToOne(mappedBy = "ruleFormula")
    private RuleEntity ruleEntity;

    @Override
    public String toString()
    {
        return "RuleFormulaEntity{" +
                "id=" + id +
                ", formula=" + Arrays.toString(formula) +
                ", metricEntityList=" + metricEntityList +
                "m ruleEntity=" + ruleEntity +
                '}';
    }

    public RuleEntity getRuleEntity()
    {
        return ruleEntity;
    }

    public void setRuleEntity(RuleEntity ruleEntity)
    {
        this.ruleEntity = ruleEntity;
    }
}
