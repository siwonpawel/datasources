package com.github.siwonpawel.datasources.configuration.datasource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties({ DataSourceProperties.class })
public class DatasourceConfiguration
{

    @Bean
    public DataSourceRouter dataSource(DataSourceProperties datasourceProperties)
    {
        return new DataSourceRouter(datasourceProperties);
    }

}
