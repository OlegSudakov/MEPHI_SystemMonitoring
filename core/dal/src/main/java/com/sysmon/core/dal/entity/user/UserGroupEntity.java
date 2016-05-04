package com.sysmon.core.dal.entity.user;

import com.sysmon.core.dal.entity.reaction.AlertTemplateEntity;
import com.sysmon.core.dal.entity.reaction.EmailTemplateEntity;
import com.sysmon.core.dal.entity.reaction.SmsTemplateEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER_GROUP")
public class UserGroupEntity implements Serializable
{
    private static final long serialVersionUID = 2421688898503247809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true, length = 128)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_USER",
            joinColumns = {@JoinColumn(name = "USER_GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<UserEntity> userEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_ALERT_TEMPLATE",
            joinColumns = {@JoinColumn(name = "USER_GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ALERT_TEMPLATE_ID")}
    )
    private List<AlertTemplateEntity> alertTemplateEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_EMAIL_TEMPLATE",
            joinColumns = {@JoinColumn(name = "USER_GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "EMAIL_TEMPLATE_ID")}
    )
    private List<EmailTemplateEntity> emailTemplateEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_SMS_TEMPLATE",
            joinColumns = {@JoinColumn(name = "USER_GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SMS_TEMPLATE_ID")}
    )
    private List<SmsTemplateEntity> smsTemplateEntityList;

    @Override
    public String toString()
    {
        return "UserGroupEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userEntityList=" + userEntityList +
                ", alertTemplateEntityList=" + alertTemplateEntityList +
                ", emailTemplateEntityList=" + emailTemplateEntityList +
                ", smsTemplateEntityList=" + smsTemplateEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroupEntity that = (UserGroupEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
