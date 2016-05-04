package com.sysmon.core.dal.entity.rule;

import com.sysmon.core.dal.entity.formula.RuleFormulaEntity;
import com.sysmon.core.dal.entity.reaction.ReactionTemplateEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "E_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "RULE")
public class RuleEntity implements Serializable
{
    private static final long serialVersionUID = 1151712912800478512L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @OneToOne
    @JoinColumn(name = "RULE_FORMULA_ID", referencedColumnName = "ID")
    protected RuleFormulaEntity ruleFormula;

    @ManyToOne
    @JoinColumn(name = "PRIORITY_ID", referencedColumnName = "ID")
    protected PriorityEntity priorityEntity;

    @ManyToMany
    @JoinTable(
            name = "RULE_REACTION_TEMPLATE",
            joinColumns = {@JoinColumn(name = "RULE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "REACTION_TEMPLATE_ID")}
    )
    protected List<ReactionTemplateEntity> reactionTemplateEntityList;

    @Override
    public String toString()
    {
        return "RuleEntity{" +
                "id=" + id +
                ", ruleFormula=" + ruleFormula +
                ", priorityEntity=" + priorityEntity +
                ", reactionTemplateEntityList=" + reactionTemplateEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleEntity that = (RuleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ruleFormula != null ? !ruleFormula.equals(that.ruleFormula) : that.ruleFormula != null) return false;
        return priorityEntity != null ? priorityEntity.equals(that.priorityEntity) : that.priorityEntity == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ruleFormula != null ? ruleFormula.hashCode() : 0);
        result = 31 * result + (priorityEntity != null ? priorityEntity.hashCode() : 0);
        return result;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public RuleFormulaEntity getRuleFormula()
    {
        return ruleFormula;
    }

    public void setRuleFormula(RuleFormulaEntity ruleFormula)
    {
        this.ruleFormula = ruleFormula;
    }

    public PriorityEntity getPriorityEntity()
    {
        return priorityEntity;
    }

    public void setPriorityEntity(PriorityEntity priorityEntity)
    {
        this.priorityEntity = priorityEntity;
    }

    public List<ReactionTemplateEntity> getReactionTemplateEntityList()
    {
        return reactionTemplateEntityList;
    }

    public void setReactionTemplateEntityList(List<ReactionTemplateEntity> reactionTemplateEntityList)
    {
        this.reactionTemplateEntityList = reactionTemplateEntityList;
    }
}
