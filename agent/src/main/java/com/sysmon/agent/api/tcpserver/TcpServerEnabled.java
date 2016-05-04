package com.sysmon.agent.api.tcpserver;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TcpServerEnabled implements Condition
{
    @Override
    public boolean matches(
            ConditionContext context,
            AnnotatedTypeMetadata metadata
    )
    {
        String property = context.getEnvironment().getProperty("tcpserver.enabled");
        return "true".equalsIgnoreCase(property);
    }
}
