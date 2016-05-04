package com.sysmon.core.dal.entity.reaction;

import javax.persistence.*;

@Entity
@Table(name = "ALERT")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class AlertEntity extends ReactionEntity
{
    private static final long serialVersionUID = 5220425148793014705L;

    @Override
    public String toString()
    {
        return "AlertEntity{" +
                "id=" + id +
                ", ruleCheckResult=" + ruleCheckResult +
                ", reactionTemplateEntity=" + reactionTemplateEntity +
                '}';
    }
}
