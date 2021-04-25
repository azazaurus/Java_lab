package ru.itis.config;

import lombok.*;
import org.springframework.core.env.*;
import org.springframework.core.io.support.*;
import org.springframework.web.*;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;
import org.springframework.web.servlet.*;

import javax.servlet.*;

public class ApplicationInitializer implements WebApplicationInitializer {
    @SneakyThrows
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        PropertySource propertySource = new ResourcePropertySource("classpath:application.properties");
        springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("spring.profile"));

        springWebContext.register(ApplicationConfig.class);
        servletContext.addListener(new ContextLoaderListener(springWebContext));

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
