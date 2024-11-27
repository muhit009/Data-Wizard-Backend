package com.datawizard.backend.datawizardbackend.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataWizardsDS")
    public DataSource getDataWizardsDataSource() {
        //noinspection rawtypes
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(System.getenv("DATA_WIZARDS_DB_URL"));
        dataSourceBuilder.username(System.getenv("DATA_WIZARDS_DB_USER"));
        dataSourceBuilder.password(System.getenv("DATA_WIZARDS_DB_PASSWORD"));
        return dataSourceBuilder.build();
    }
}