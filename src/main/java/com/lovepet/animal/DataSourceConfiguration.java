package com.lovepet.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    // 連線到 animal 資料庫的 DataSource 和 NamedParameterJdbcTemplate
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.animal")
    public DataSource animalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate animalJdbcTemplate(
            @Qualifier("animalDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


    // 連線到 forum 資料庫的 DataSource 和 NamedParameterJdbcTemplate
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.forum")
    public DataSource forumDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate forumJdbcTemplate(
            @Qualifier("forumDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}