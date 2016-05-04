package com.sysmon.core.dal.entity.reaction;

import com.sysmon.core.dal.entity.rule.RuleCheckResult;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "E_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "REACTION")
public class ReactionEntity implements Serializable
{
    private static final long serialVersionUID = -1396395612534727785L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "RULE_CHECK_RESULE_ID", referencedColumnName = "ID")
    protected RuleCheckResult ruleCheckResult;

    @ManyToOne
    @JoinColumn(name = "REACTION_TEMPLATE_ID", referencedColumnName = "ID")
    protected ReactionTemplateEntity reactionTemplateEntity;

    @Override
    public String toString()
    {
        return "ReactionEntity{" +
                "id=" + id +
                ", ruleCheckResult=" + ruleCheckResult +
                ", reactionTemplateEntity=" + reactionTemplateEntity +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ReactionEntity)) return false;

        ReactionEntity reactionEntity = (ReactionEntity) o;

        if (getId() != null ? !getId().equals(reactionEntity.getId()) : reactionEntity.getId() != null) return false;
        if (getRuleCheckResult() != null ? !getRuleCheckResult().equals(reactionEntity.getRuleCheckResult()) : reactionEntity.getRuleCheckResult() != null)
            return false;
        return getReactionTemplateEntity() != null ? getReactionTemplateEntity().equals(reactionEntity.getReactionTemplateEntity()) : reactionEntity.getReactionTemplateEntity() == null;
    }

    @Override
    public int hashCode()
    {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getRuleCheckResult() != null ? getRuleCheckResult().hashCode() : 0);
        result = 31 * result + (getReactionTemplateEntity() != null ? getReactionTemplateEntity().hashCode() : 0);
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

    public RuleCheckResult getRuleCheckResult()
    {
        return ruleCheckResult;
    }

    public void setRuleCheckResult(RuleCheckResult ruleCheckResult)
    {
        this.ruleCheckResult = ruleCheckResult;
    }

    public ReactionTemplateEntity getReactionTemplateEntity()
    {
        return reactionTemplateEntity;
    }

    public void setReactionTemplateEntity(ReactionTemplateEntity reactionTemplateEntity)
    {
        this.reactionTemplateEntity = reactionTemplateEntity;
    }
}
