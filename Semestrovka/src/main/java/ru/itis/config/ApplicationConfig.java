package ru.itis.config;

import com.zaxxer.hikari.*;
import freemarker.cache.*;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.*;
import org.springframework.core.io.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.freemarker.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.*;
import ru.itis.util.*;

import javax.persistence.*;
import javax.sql.*;
import java.util.*;
import java.util.concurrent.*;

@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.itis.repositories")
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.itis")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        return new FreeMarkerConfigurer();
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        FreeMarkerConfigurer configurer = freemarkerConfig();
        freemarker.template.Configuration configuration = configurer.getConfiguration();

        configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateLoader(configureTemplateLoader());
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("ru.itis.model");
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Profile("master")
    @Bean
    public EmailUtilImpl emailUtil() {
        Properties mailProperties = PropertyUtil.getProperties(environment, "mail.");
        return new EmailUtilImpl(mailProperties);
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }

    private TemplateLoader configureTemplateLoader() {
        // Загружает spring.ftl
		ClassTemplateLoader baseMvcTplLoader = new ClassTemplateLoader(
			FreeMarkerConfigurer.class,
			"");

        // Загружает ftl-файлы из папки resources/pages/
		SpringTemplateLoader pagesTemplateLoader = new SpringTemplateLoader(
			new ClassRelativeResourceLoader(this.getClass()),
			"/pages/");

        // Загружает ftl-файлы из папки resources/mails/
		SpringTemplateLoader mailsTemplateLoader = new SpringTemplateLoader(
			new ClassRelativeResourceLoader(this.getClass()),
			"/mails/");

		return new MultiTemplateLoader(
			new TemplateLoader[] { baseMvcTplLoader, pagesTemplateLoader, mailsTemplateLoader });
	}
}
