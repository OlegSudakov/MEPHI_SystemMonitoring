package com.sysmon.wi;

import com.sun.faces.config.ConfigureListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

public class SVWIApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext)
    {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SVWIApplicationConfig.class);
        rootContext.setServletContext(servletContext);

        servletContext.addListener(new ConfigureListener());
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.addListener(new RequestContextListener());

//        Dynamic dynamic= servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
//        dynamic.addMapping("/");
//        dynamic.setLoadOnStartup(1);
    }
}