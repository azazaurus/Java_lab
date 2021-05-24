package ru.itis.config;

import org.springframework.core.env.*;

import java.util.*;

public class PropertyUtil {
    public static Properties getProperties(Environment environment) {
        return getProperties(environment, null);
    }

    public static Properties getProperties(Environment environment, String withPrefix) {
        Properties properties = new Properties();
        if (environment instanceof ConfigurableEnvironment)
            for (PropertySource<?> propertySource
                    : ((ConfigurableEnvironment)environment).getPropertySources())
                if (propertySource instanceof EnumerablePropertySource)
                    for (String key : ((EnumerablePropertySource)propertySource).getPropertyNames())
                        if (withPrefix == null || key.startsWith(withPrefix))
                            properties.putIfAbsent(key, propertySource.getProperty(key));
        return properties;
    }
}
