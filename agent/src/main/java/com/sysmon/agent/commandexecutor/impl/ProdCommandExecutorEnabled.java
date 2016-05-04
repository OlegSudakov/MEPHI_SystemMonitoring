package com.sysmon.agent.commandexecutor.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ProdCommandExecutorEnabled implements Condition
{
    @Override
    public boolean matches(
            ConditionContext context,
            AnnotatedTypeMetadata metadata
    )
    {
        String property = context.getEnvironment().getProperty("commandexecutor.type");
        return "prod".equalsIgnoreCase(property);
    }
}
