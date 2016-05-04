package com.sysmon.core.dal.entity.rule;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RULE_CHECK_RESULT")
public class RuleCheckResult implements Serializable
{
    private static final long serialVersionUID = -3724114307074449971L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RULE_ID", referencedColumnName = "ID")
    private RuleEntity ruleEntity;

    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Override
    public String toString()
    {
        return "RuleCheckResult{" +
                "id=" + id +
                ", ruleEntity=" + ruleEntity +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleCheckResult that = (RuleCheckResult) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ruleEntity != null ? !ruleEntity.equals(that.ruleEntity) : that.ruleEntity != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ruleEntity != null ? ruleEntity.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
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

    public RuleEntity getRuleEntity()
    {
        return ruleEntity;
    }

    public void setRuleEntity(RuleEntity ruleEntity)
    {
        this.ruleEntity = ruleEntity;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }
}
