package com.sysmon.core.dal;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ContainerEnabled implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
    {
        String property = context.getEnvironment().getProperty("datasourcetype");
        return "jndi".equalsIgnoreCase(property);
    }
}
