package com.sysmon.core.server;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

public class ServerApplicationInitializer implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext container)
    {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ServerConfig.class);

        container.addListener(new ContextLoaderListener(rootContext));
    }
}
