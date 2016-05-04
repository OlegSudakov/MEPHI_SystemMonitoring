package com.sysmon.core.dal.entity.reaction;

import com.sysmon.core.dal.entity.user.UserEntity;
import com.sysmon.core.dal.entity.user.UserGroupEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EMAIL_TEMPLATE")
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class EmailTemplateEntity extends ReactionTemplateEntity
{
    private static final long serialVersionUID = 1347482173949073888L;

    @Column(name = "SUBJECT", nullable = false, length = 256)
    private String subject;

    @Column(name = "TEMPLATE", nullable = false, length = 2048)
    private String template;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_EMAIL_TEMPLATE",
            joinColumns = {@JoinColumn(name = "EMAIL_TEMPLATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_GROUP_ID")}
    )
    private List<UserGroupEntity> userGroupEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_EMAIL_TEMPLATE",
            joinColumns = {@JoinColumn(name = "EMAIL_TEMPLATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<UserEntity> userEntityList;

    @Override
    public String toString()
    {
        return "EmailTemplateEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", template='" + template + '\'' +
                ", userGroupEntityList=" + userGroupEntityList +
                ", userEntityList=" + userEntityList +
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

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }

    public List<UserGroupEntity> getUserGroupEntityList()
    {
        return userGroupEntityList;
    }

    public void setUserGroupEntityList(List<UserGroupEntity> userGroupEntityList)
    {
        this.userGroupEntityList = userGroupEntityList;
    }

    public List<UserEntity> getUserEntityList()
    {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList)
    {
        this.userEntityList = userEntityList;
    }
}
