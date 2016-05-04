package com.sysmon.core.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ServerApplication
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(ServerConfig.class);
        while (Thread.currentThread().isInterrupted()) {
            Thread.sleep(Long.MAX_VALUE);
        }
    }
}
