package com.fiap.orquestrador_only_videos.adapter.broker.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class H2Config {

	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
	
	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	@Bean
	public void initializeH2(JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS solicitacoesUpload (guid VARCHAR(36) PRIMARY KEY, status VARCHAR(20)), s3Link VARCHAR(1000)");
	}

	
}
