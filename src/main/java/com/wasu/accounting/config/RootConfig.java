package com.wasu.accounting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * Created by yangzh on 2017/2/23.
 */
@Configuration
@ComponentScan(basePackages = "com.wasu.accounting.service")
@PropertySource(value = "classpath:/datasource.properties")
@Import(DataSourceConfig.class)
public class RootConfig {

    @Autowired
    Environment env;

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        return new PropertyPlaceholderConfigurer();
    }


    @Bean
    public Object obj(){
        return "1";
    }
}
