package com.sysmon.core.dal.entity.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserSettingEntityPK implements Serializable
{
    private static final long serialVersionUID = 8413545263883530928L;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "KEY", nullable = false, length = 128)
    private String key;

    public UserSettingEntityPK() {}

    public UserSettingEntityPK(
            Long userId,
            String key
    )
    {
        this.userId = userId;
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "UserSettingEntityPK{" +
                "userId=" + userId +
                ", key='" + key + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserSettingEntityPK)) return false;

        UserSettingEntityPK that = (UserSettingEntityPK) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        return getKey() != null ? getKey().equals(that.getKey()) : that.getKey() == null;
    }

    @Override
    public int hashCode()
    {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getKey() != null ? getKey().hashCode() : 0);
        return result;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
