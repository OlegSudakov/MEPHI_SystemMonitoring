package com.sysmon.core.dal.entity.reaction;

import com.sysmon.core.dal.entity.user.UserEntity;
import com.sysmon.core.dal.entity.user.UserGroupEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SMS_TEMPLATE")
@DiscriminatorValue("3")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class SmsTemplateEntity extends ReactionTemplateEntity
{
    private static final long serialVersionUID = -7081192208894665591L;

    @Column(name = "TEMPLATE", nullable = false, length = 2048)
    private String template;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_SMS_TEMPLATE",
            joinColumns = {@JoinColumn(name = "SMS_TEMPLATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_GROUP_ID")}
    )
    private List<UserGroupEntity> userGroupEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_SMS_TEMPLATE",
            joinColumns = {@JoinColumn(name = "SMS_TEMPLATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<UserEntity> userEntityList;

    @Override
    public String toString()
    {
        return "SmsTemplateEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", template='" + template + '\'' +
                ", userGroupEntityList=" + userGroupEntityList +
                ", userEntityList=" + userEntityList +
                '}';
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
