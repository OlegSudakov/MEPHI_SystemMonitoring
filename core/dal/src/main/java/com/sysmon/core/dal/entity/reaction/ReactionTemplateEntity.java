package com.sysmon.core.dal.entity.reaction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "E_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "REACTION_TEMPLATE")
public class ReactionTemplateEntity implements Serializable
{
    private static final long serialVersionUID = 3295965606716428383L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @Column(name = "NAME", nullable = false, length = 256)
    protected String name;

    @Override
    public String toString()
    {
        return "ReactionTemplateEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ReactionTemplateEntity)) return false;

        ReactionTemplateEntity that = (ReactionTemplateEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode()
    {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
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
}
