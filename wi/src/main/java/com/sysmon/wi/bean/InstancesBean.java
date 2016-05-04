package com.sysmon.wi.bean;

import com.sysmon.core.dal.entity.instance.InstanceEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@ManagedBean
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Component
public class InstancesBean
{
    static Integer pagRowsCount = 3;

    //// TODO: Get and save this value from/to user service
    @Min(1)
    @Max(50)
    @NotNull
    private Integer paginationRowsCount = pagRowsCount;

    private List<InstanceEntity> instanceEntities;

    public List<InstanceEntity> getInstanceEntities()
    {
        return Collections.emptyList();
    }

    public Integer getPaginationRowsCount()
    {
        return paginationRowsCount;
    }

    public void setPaginationRowsCount(Integer paginationRowsCount)
    {
        this.paginationRowsCount = pagRowsCount= paginationRowsCount;
    }
}
