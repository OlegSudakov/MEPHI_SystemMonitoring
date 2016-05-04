package com.sysmon.core.ruleengine;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {
                "com.sysmon.core.ruleengine"
        }
)
public class RuleEngineConfig
{
}
