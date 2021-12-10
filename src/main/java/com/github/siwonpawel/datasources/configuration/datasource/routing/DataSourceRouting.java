package com.github.siwonpawel.datasources.configuration.datasource.routing;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.siwonpawel.datasources.configuration.datasource.DataSourceRouter;

@Aspect
@Component
public class DataSourceRouting
{

    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod()
    {
        // NOSONAR
    }

    @Pointcut("within(com.github.siwonpawel.datasources.controller.*)")
    private void inDataSources()
    {
        // NOSONAR
    }

    @Pointcut(value = "@annotation(com.github.siwonpawel.datasources.configuration.datasource.routing.RoutedDataSource)")
    private void markedWithRoutedDataSource()
    {
        // NOSONAR
    }

    @Pointcut("anyPublicMethod() && inDataSources() && markedWithRoutedDataSource()")
    private void anyPublicInDataSourcesWithRoutedDataSource()
    {
        // NOSONAR
    }

    @Before(value = "anyPublicInDataSourcesWithRoutedDataSource() && args(key1,key2,..)", argNames = "key1,key2")
    public void applyDataSourceRouting(Integer key1, Integer key2)
    {
        DataSourceRouter.setDataSourceLookupKey(key1, key2);
    }

    @After("anyPublicInDataSourcesWithRoutedDataSource()")
    @AfterThrowing("anyPublicInDataSourcesWithRoutedDataSource()")
    public void clearDataSourceRouting()
    {
        DataSourceRouter.clear();
    }

}
