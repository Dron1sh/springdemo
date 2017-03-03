package com.wasu.accounting.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by yangzh on 2017/2/23.
 */
@Configuration
public class DataSourceConfig implements EnvironmentAware{

    Environment env;

    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("db.jdbc.url"));
        dataSource.setUsername(env.getProperty("db.jdbc.username"));
        dataSource.setPassword(env.getProperty("db.jdbc.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("db.initialsize")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("db.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("db.maxactive")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("db.TimeBetweenEvictionRunsMillis")));
        try {
            dataSource.setFilters(env.getProperty("db.filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.wasu.accounting.dao");
        Properties properties = new Properties();
        properties.put("mappers", "tk.mybatis.mapper.common.Mapper");
        configurer.setProperties(properties);
        return configurer;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(10000);
        filter.setLogSlowSql(true);
        return filter;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
