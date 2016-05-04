package com.sysmon.agent;

import com.sysmon.agent.commandexecutor.CommandExecutorConfig;
import com.sysmon.agent.metriccollector.MetricCollectorConfig;
import com.sysmon.agent.api.ApiConfig;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({
        MetricCollectorConfig.class,
        CommandExecutorConfig.class,
        ApiConfig.class
})
@PropertySource("file:${appConfig:application.properties}")
public class AgentApplicationConfig
{
    private static final Logger log = Logger.getLogger(AgentApplicationConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean()
    {
        return new ConversionServiceFactoryBean();
    }
}
