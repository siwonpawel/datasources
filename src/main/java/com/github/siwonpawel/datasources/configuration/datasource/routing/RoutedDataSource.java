package com.github.siwonpawel.datasources.configuration.datasource.routing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoutedDataSource
{

    String companyId() default "companyId";
    String advertId() default "advertisementId";

}