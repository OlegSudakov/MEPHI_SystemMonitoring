package com.sysmon.core.dal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

@Configuration
@Conditional(ContainerEnabled.class)
public class ContainerConfig {
    private Logger log = Logger.getLogger(ContainerConfig.class);

    @Bean
    public JndiObjectFactoryBean sysmonJndiObjectFactory(@Value("${datasource.sysmon.jndiname}") String sysmonJndiName)
    {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName(sysmonJndiName);
        jndiObjectFactoryBean.setLookupOnStartup(false);
        jndiObjectFactoryBean.setCache(true);
        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
        return jndiObjectFactoryBean;
    }

    @Bean
    public DataSource dataSource(JndiObjectFactoryBean sysmonJndiObjectFactory)
    {
        return (DataSource) sysmonJndiObjectFactory.getObject();
    }
}
