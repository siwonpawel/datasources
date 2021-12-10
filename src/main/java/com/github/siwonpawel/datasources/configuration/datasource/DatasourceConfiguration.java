package com.github.siwonpawel.datasources.configuration.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties({ DataSourceProperties.class })
public class DatasourceConfiguration
{

    @Bean
    public DataSource getDataSource(DataSourceProperties datasourceProperties, Map<Object, DataSource> companyDatabases)
    {
        var defaultDataSource = createDataSource(
                datasourceProperties.getDefaultUrl(),
                datasourceProperties.getDefaultUsername(),
                datasourceProperties.getDefaultPassword()
        );

        return new DataSourceRouter(defaultDataSource, companyDatabases);
    }

    @Bean
    public Map<Object, DataSource> getCompanyDatabases()
    {
        return new HashMap<>();
    }

    public static DataSource createDataSource(String url, String username, String password)
    {
        return DataSourceBuilder.create()
                .driverClassName("org.mariadb.jdbc.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
