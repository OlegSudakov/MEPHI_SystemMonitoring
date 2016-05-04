package com.sysmon.core.dal.entity.reaction;

import javax.persistence.*;

@Entity
@Table(name = "EMAIL")
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class EmailEntity extends ReactionEntity
{
    private static final long serialVersionUID = -5748144457426615659L;

    @Column(name = "SUBJECT", nullable = false, length = 256)
    private String subject;

    @Column(name = "TEXT", nullable = false, length = 4096)
    private String text;

    @Override
    public String toString()
    {
        return "EmailEntity{" +
                "id=" + id +
                ", ruleCheckResult=" + ruleCheckResult +
                ", reactionTemplateEntity=" + reactionTemplateEntity +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
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
