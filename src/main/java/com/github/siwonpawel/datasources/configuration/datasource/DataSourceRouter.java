package com.github.siwonpawel.datasources.configuration.datasource;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ObjectUtils;

public class DataSourceRouter extends AbstractRoutingDataSource
{
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    Map<Object, DataSource> dataSources;

    public DataSourceRouter(DataSource defaultDataSource, Map<Object, DataSource> dataSources)
    {
        super();
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(Collections.unmodifiableMap(dataSources));
        this.dataSources = dataSources;
    }

    @Override
    protected Object determineCurrentLookupKey()
    {
        return threadLocal.get();
    }

    @Override
    protected DataSource determineTargetDataSource()
    {
        Object lookupKey = determineCurrentLookupKey();
        if (ObjectUtils.isEmpty(lookupKey))
        {
            return getResolvedDefaultDataSource();
        }
        else
        {
            return dataSources.get(lookupKey);
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
}
