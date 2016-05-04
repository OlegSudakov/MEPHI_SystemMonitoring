package com.sysmon.core.server;

import com.sysmon.core.dal.DalConfig;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jmx.export.MBeanExporter;

@Configuration
@PropertySource("file:${appConfig:application.properties}")
@ComponentScan(
        basePackages = {
                "com.sysmon.core.server"
        }
)
@Import({
        DalConfig.class
})
public class ServerConfig
{
    private static final Logger log = Logger.getLogger(ServerConfig.class);

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

    @Bean
    @Lazy(false)
    public MBeanExporter mBeanExporter()
    {
        return new MBeanExporter();
    }
}
