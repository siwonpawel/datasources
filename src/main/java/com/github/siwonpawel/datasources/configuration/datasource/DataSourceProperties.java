package com.github.siwonpawel.datasources.configuration.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "datasources.config.db")
public class DataSourceProperties
{

    private String defaultUrl;
    private String defaultUsername;
    private String defaultPassword;

    private String url;
    private String username;
    private String password;

}
