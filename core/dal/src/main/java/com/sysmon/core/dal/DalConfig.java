package com.sysmon.core.dal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.*;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.sysmon.core.dal")
@EnableJpaRepositories("com.sysmon.core.dal.repository")
@Import({
        ContainerConfig.class,
        LocalConfig.class
})
@PropertySource("file:${dbConfig:database.properties}")
public class DalConfig
{
    private Logger log = Logger.getLogger(DalConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter(
            @Value("${jpsvendoradapter.showsql}") Boolean showSql,
            @Value("${jpavendoradapter.datapaseplatform}") String databasePlatform,
            @Value("${jpavendoradapter.database}") Database database
    )
    {
        EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(showSql);
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setDatabasePlatform(databasePlatform);
        jpaVendorAdapter.setDatabase(database);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            JpaVendorAdapter jpaVendorAdapter,
            DataSource dataSource
    ) throws NamingException
    {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(jpaVendorAdapter);

        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("eclipselink.weaving", "static");
        jpaProperties.put("eclipselink.profiler", "QueryMonitor");
        jpaProperties.put("eclipselink.logging.level", "ALL");
        jpaProperties.put("eclipselink.logging.level.sql", "ALL");
        jpaProperties.put("eclipselink.logging.parameters", "true");
        jpaProperties.put("eclipselink.logging.timestamp", "true");
        jpaProperties.put("eclipselink.logging.session", "true");
        jpaProperties.put("eclipselink.logging.thread", "true");
        jpaProperties.put("eclipselink.logging.exceptions", "true");
        jpaProperties.put("eclipselink.logging.logger", "org.eclipse.persistence.logging.slf4j.Slf4jSessionLogger");

        emf.setJpaPropertyMap(jpaProperties);
        emf.setPersistenceProviderClass(org.eclipse.persistence.jpa.PersistenceProvider.class);
        emf.setPackagesToScan("com.sysmon.core.dal.entity");
        emf.setPersistenceUnitName("sysmonpersistence");
        emf.setDataSource(dataSource);
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory,
            DataSource dataSource
    )
    {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setPersistenceUnitName("sysmonpersistence");
        transactionManager.setJpaDialect(new EclipseLinkJpaDialect());
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
