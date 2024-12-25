package com.logblock.backend.DataSource.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.logblock.backend.DataSource.Repository")
public class PrimaryDataSourceConfig {
    
    @Autowired
    private Environment env;

	@Bean
	public HikariDataSource primaryDataSource() {
		return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .username(env.getProperty("logblock.datasource.username"))
        .password(env.getProperty("logblock.datasource.password"))
        .driverClassName(env.getProperty("logblock.datasource.driverClassName"))
        .url(env.getProperty("logblock.datasource.jdbc-url"))
        .build();
	}
}
