package com.sysmon.core.dal.entity.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "USER_SETTING")
public class UserSettingEntity implements Serializable
{
    private static final long serialVersionUID = -7253740431451257090L;

    @EmbeddedId
    private UserSettingEntityPK pk;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private UserEntity userEntity;

    @Column(name = "VALUE")
    @Lob
    private byte[] value;

    public UserSettingEntity() {}

    public UserSettingEntity(
            UserEntity userEntity,
            String key,
            byte[] value
    )
    {
        this(new UserSettingEntityPK(userEntity.getId(), key), value);
    }

    public UserSettingEntity(
            UserSettingEntityPK pk,
            byte[] value
    )
    {
        this.pk = pk;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "UserSettingEntity{" +
                "pk=" + pk +
                ", userEntity=" + userEntity +
                ", value=" + Arrays.toString(value) +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSettingEntity that = (UserSettingEntity) o;

        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;
        return userEntity != null ? userEntity.equals(that.userEntity) : that.userEntity == null;

    }

    @Override
    public int hashCode()
    {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (userEntity != null ? userEntity.hashCode() : 0);
        return result;
    }

    public UserSettingEntityPK getPk()
    {
        return pk;
    }

    public void setPk(UserSettingEntityPK pk)
    {
        this.pk = pk;
    }

    public UserEntity getUserEntity()
    {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }

    public byte[] getValue()
    {
        return value;
    }

    public void setValue(byte[] value)
    {
        this.value = value;
    }
}
