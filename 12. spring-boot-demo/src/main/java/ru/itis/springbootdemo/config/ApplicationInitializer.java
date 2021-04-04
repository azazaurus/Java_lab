package ru.itis.springbootdemo.config;

import org.springframework.boot.web.reactive.context.*;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.*;
import org.springframework.web.*;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;
import org.springframework.web.servlet.*;
import javax.servlet.*;
import java.io.*;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext =
                new AnnotationConfigWebApplicationContext();

        try {
            ResourcePropertySource propertySource = new ResourcePropertySource("application.properties");
            springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("spring.profile"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        springWebContext.register(ApplicationConfig.class);

        servletContext.addListener(new ContextLoaderListener(springWebContext));

        ServletRegistration.Dynamic dispatcherServlet = servletContext
                .addServlet("dispatcher", new DispatcherServlet());


    }
}
