package com.sysmon.agent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class AgentApplication
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AgentApplicationConfig.class);
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(Long.MAX_VALUE);
        }
    }
}