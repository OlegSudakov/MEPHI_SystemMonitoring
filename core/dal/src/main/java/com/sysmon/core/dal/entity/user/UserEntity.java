package com.sysmon.core.dal.entity.user;

import com.sysmon.core.dal.entity.reaction.AlertTemplateEntity;
import com.sysmon.core.dal.entity.reaction.EmailTemplateEntity;
import com.sysmon.core.dal.entity.reaction.SmsTemplateEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable
{
    private static final long serialVersionUID = 9029745547865977358L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NICKNAME", nullable = false, unique = true, length = 128)
    private String nickname;

    @Column(name = "FULLNAME", nullable = false, length = 256)
    private String fullname;

    @Column(name = "EMAIL", nullable = false, length = 128)
    private String email;

    @Column(name = "PHONE", nullable = false, length = 128)
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_USER",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_GROUP_ID")}
    )
    private List<UserGroupEntity> userList;

    @ManyToMany
    @JoinTable(
            name = "USER_ALERT_TEMPLATE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ALERT_TEMPLATE_ID")}
    )
    private List<AlertTemplateEntity> alertTemplateEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_EMAIL_TEMPLATE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "EMAIL_TEMPLATE_ID")}
    )
    private List<EmailTemplateEntity> emailTemplateEntityList;

    @ManyToMany
    @JoinTable(
            name = "USER_SMS_TEMPLATE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SMS_TEMPLATE_ID")}
    )
    private List<SmsTemplateEntity> smsTemplateEntityList;

    @Override
    public String toString()
    {
        return "UserEntity{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userList=" + userList +
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

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
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

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public List<UserGroupEntity> getUserList()
    {
        return userList;
    }

    public void setUserList(List<UserGroupEntity> userList)
    {
        this.userList = userList;
    }
}
