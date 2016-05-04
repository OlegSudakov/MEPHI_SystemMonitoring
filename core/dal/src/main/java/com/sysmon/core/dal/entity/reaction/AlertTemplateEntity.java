package com.sysmon.core.dal.entity.reaction;

import com.sysmon.core.dal.entity.user.UserEntity;
import com.sysmon.core.dal.entity.user.UserGroupEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ALERT_TEMPLATE")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class AlertTemplateEntity extends ReactionTemplateEntity
{
    private static final long serialVersionUID = 5754617489796590849L;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_ALERT_TEMPLATE",
            joinColumns = {@JoinColumn(name = "ALERT_TEMPLATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_GROUP_ID")}
    )
    private List<UserGroupEntity> userGroupEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_ALERT_TEMPLATE",
            joinColumns = {@JoinColumn(name = "ALERT_TEMPLATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<UserEntity> userEntityList;

    @Override
    public String toString()
    {
        return "AlertTemplateEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userGroupEntityList=" + userGroupEntityList +
                ", userEntityList=" + userEntityList +
                '}';
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
