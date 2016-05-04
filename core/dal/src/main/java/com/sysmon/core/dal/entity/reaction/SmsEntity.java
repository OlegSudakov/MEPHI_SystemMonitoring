package com.sysmon.core.dal.entity.reaction;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "SMS")
@DiscriminatorValue("3")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class SmsEntity extends ReactionEntity
{
    private static final long serialVersionUID = 1673390437784326408L;

    @Column(name = "TEXT", nullable = false, length = 4096)
    private String text;

    @Override
    public String toString()
    {
        return "SmsEntity{" +
                "id=" + id +
                ", ruleCheckResult=" + ruleCheckResult +
                ", reactionTemplateEntity=" + reactionTemplateEntity +
                ", text='" + text + '\'' +
                '}';
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
