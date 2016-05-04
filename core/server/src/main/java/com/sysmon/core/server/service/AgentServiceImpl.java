package com.sysmon.core.server.service;

import com.sysmon.core.dal.dao.AgentDao;
import com.sysmon.core.dal.dao.InstanceDao;
import com.sysmon.core.dal.dao.JmxAgentDao;
import com.sysmon.core.dal.dao.LinuxAgentDao;
import com.sysmon.core.dal.entity.agent.AgentEntity;
import com.sysmon.core.dal.entity.agent.JmxAgentEntity;
import com.sysmon.core.dal.entity.agent.LinuxAgentEntity;
import com.sysmon.core.dal.entity.instance.InstanceEntity;
import com.sysmon.core.server.agent.AgentConnection;
import com.sysmon.core.server.agent.AgentConnectionType;
import com.sysmon.core.server.agent.CommonAgentConnectionManager;
import com.sysmon.core.server.agent.jmx.JmxAgentConnection;
import com.sysmon.core.server.agent.linux.LinuxAgentConnection;
import com.sysmon.core.server.utils.AgentEntityToAgentConnectionConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService
{
    private static final Logger log = Logger.getLogger(AgentServiceImpl.class);

    private final AgentDao agentDao;
    private final CommonAgentConnectionManager commonAgentConnectionManager;
    private final InstanceDao instanceDao;
    private final LinuxAgentDao linuxAgentDao;
    private final JmxAgentDao jmxAgentDao;
    private final AgentEntityToAgentConnectionConverter agentEntityToAgentConnectionConverter;

    @Autowired
    public AgentServiceImpl(
            AgentDao agentDao,
            CommonAgentConnectionManager commonAgentConnectionManager,
            InstanceDao instanceDao,
            LinuxAgentDao linuxAgentDao,
            JmxAgentDao jmxAgentDao,
            AgentEntityToAgentConnectionConverter agentEntityToAgentConnectionConverter
    )
    {
        this.agentDao = agentDao;
        this.commonAgentConnectionManager = commonAgentConnectionManager;
        this.instanceDao = instanceDao;
        this.linuxAgentDao = linuxAgentDao;
        this.jmxAgentDao = jmxAgentDao;
        this.agentEntityToAgentConnectionConverter = agentEntityToAgentConnectionConverter;
    }

    private void fillAgentEntity(
            AgentEntity agent,
            Long instanceId
    )
    {
        Optional<InstanceEntity> instanceEntityOptional = instanceDao.getInstanceById(instanceId);
        if (!instanceEntityOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("No instance with id %d found", instanceId));
        }
        agent.setInstanceEntity(instanceEntityOptional.get());
    }

    @Override
    @Transactional
    public void addLinuxAgent(
            Long instanceId,
            String host,
            Integer port
    )
    {
        LinuxAgentEntity agent = new LinuxAgentEntity();
        fillAgentEntity(agent, instanceId);
        agent.setHost(host);
        agent.setPort(port);
        AgentEntity savedAgent = agentDao.save(agent);
        AgentConnection agentConnection = agentEntityToAgentConnectionConverter.apply(savedAgent);
        commonAgentConnectionManager.add(agentConnection);
    }

    @Override
    @Transactional
    public void updateLinuxAgent(
            Long agentId,
            Long instanceId,
            String host,
            Integer port
    )
    {
        Optional<AgentConnection> agentConnectionOptional = commonAgentConnectionManager.getById(agentId);
        if (!agentConnectionOptional.isPresent() || !AgentConnectionType.LINUX.equals(agentConnectionOptional.get().getAgentConnectionType())) {
            throw new IllegalArgumentException(String.format("No linux agent with id %d found", agentId));
        }

        Optional<LinuxAgentEntity> linuxAgentEntityOptional = linuxAgentDao.getById(agentId);
        if (!linuxAgentEntityOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("No linux agent with id %d found", agentId));
        }

        LinuxAgentEntity linuxAgentEntity = linuxAgentEntityOptional.get();
        fillAgentEntity(linuxAgentEntity, instanceId);
        linuxAgentEntity.setHost(host);
        linuxAgentEntity.setPort(port);
        linuxAgentDao.save(linuxAgentEntity);
        commonAgentConnectionManager.remove(agentId);
        LinuxAgentConnection linuxAgentConnection = new LinuxAgentConnection(
                linuxAgentEntity.getId(),
                linuxAgentEntity.getHost(),
                linuxAgentEntity.getPort()
        );
        commonAgentConnectionManager.add(linuxAgentConnection);
    }

    @Override
    @Transactional
    public void addJmxAgent(
            Long instanceId,
            String host,
            Integer port
    )
    {
        JmxAgentEntity agent = new JmxAgentEntity();
        fillAgentEntity(agent, instanceId);
        agent.setHost(host);
        agent.setPort(port);
        jmxAgentDao.save(agent);
        AgentEntity savedAgent = agentDao.save(agent);
        AgentConnection agentConnection = agentEntityToAgentConnectionConverter.apply(savedAgent);
        commonAgentConnectionManager.add(agentConnection);
    }

    @Override
    @Transactional
    public void updateJmxAgent(
            Long agentId,
            Long instanceId,
            String host,
            Integer port
    )
    {
        Optional<AgentConnection> agentConnectionOptional = commonAgentConnectionManager.getById(agentId);
        if (!agentConnectionOptional.isPresent() || !AgentConnectionType.JMX.equals(agentConnectionOptional.get().getAgentConnectionType())) {
            throw new IllegalArgumentException(String.format("No jmx agent with id %d found", agentId));
        }

        Optional<JmxAgentEntity> jmxAgentEntityOptional = jmxAgentDao.getById(agentId);
        if (!jmxAgentEntityOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("No jmx agent with id %d found", agentId));
        }

        JmxAgentEntity agent = jmxAgentEntityOptional.get();
        fillAgentEntity(agent, instanceId);
        agent.setHost(host);
        agent.setPort(port);
        jmxAgentDao.save(agent);
        commonAgentConnectionManager.remove(agentId);
        JmxAgentConnection jmxAgentConnection = new JmxAgentConnection(
                agent.getId(),
                agent.getHost(),
                agent.getPort()
        );
        commonAgentConnectionManager.add(jmxAgentConnection);
    }

    @Override
    @Transactional
    public void removeAgent(Long agentId)
    {
        agentDao.delete(agentId);
        commonAgentConnectionManager.remove(agentId);
    }
}
