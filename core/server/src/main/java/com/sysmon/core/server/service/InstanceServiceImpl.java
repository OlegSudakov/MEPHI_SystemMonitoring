package com.sysmon.core.server.service;

import com.sysmon.core.dal.dao.InstanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceServiceImpl implements InstanceService
{
    private final InstanceDao instanceDao;

    @Autowired
    public InstanceServiceImpl(
            InstanceDao instanceDao
    )
    {
        this.instanceDao = instanceDao;
    }
}
