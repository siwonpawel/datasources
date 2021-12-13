package com.github.siwonpawel.datasources.configuration.datasource.routing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoutingKey
{

    RoutingKeyValue value();

    public enum RoutingKeyValue {
        COMPANY_ID,
        ADVERTISEMENT_ID
    }
}
