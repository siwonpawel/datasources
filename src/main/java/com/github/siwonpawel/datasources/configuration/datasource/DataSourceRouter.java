package com.github.siwonpawel.datasources.configuration.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ObjectUtils;

public class DataSourceRouter extends AbstractRoutingDataSource
{
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private DataSourceProperties dataSourceProperties;
    private DataSource defaultDataSource;
    private Map<Object, DataSource> dataSources;

    public DataSourceRouter(DataSourceProperties dataSourceProperties)
    {
        this.dataSourceProperties = dataSourceProperties;
        this.defaultDataSource = createDataSource(
                dataSourceProperties.getDefaultUrl(),
                dataSourceProperties.getDefaultUsername(),
                dataSourceProperties.getDefaultPassword()
        );

        this.dataSources = new HashMap<>();
    }

    @Override
    protected Object determineCurrentLookupKey()
    {
        return threadLocal.get();
    }

    @Override
    public void afterPropertiesSet()
    {
        //NOSONAR
    }

    @Override
    protected DataSource determineTargetDataSource()
    {
        Object lookupKey = determineCurrentLookupKey();
        if (ObjectUtils.isEmpty(lookupKey))
        {
            return defaultDataSource;
        }
        else
        {
            return dataSources.computeIfAbsent(lookupKey, this::createDataSource);
        }
    }

    public static String getDataSourceLookupKey()
    {
        return threadLocal.get();
    }

    public static void setDataSourceLookupKey(Object companyId, Object advertisement)
    {
        threadLocal.set(String.format("c_%s_db_%s", companyId, advertisement));
    }

    public static void clear()
    {
        threadLocal.remove();
    }

    private DataSource createDataSource(Object dbName)
    {
        return createDataSource(
                dataSourceProperties.getUrl() + dbName,
                dataSourceProperties.getUsername(),
                dataSourceProperties.getPassword()
        );
    }

    private static DataSource createDataSource(String url, String username, String password)
    {
        return DataSourceBuilder.create()
                .driverClassName("org.mariadb.jdbc.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
