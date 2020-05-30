package com.github.memsup.sentence.datasource;

import com.github.enesusta.jdbc.datasource.HikariJdbcDataSource;
import com.github.enesusta.jdbc.datasource.JdbcConfiguration;

import javax.sql.DataSource;

public enum Database {
    INSTANCE;

    public DataSource getDataSource() {

        JdbcConfiguration configuration = new JdbcConfiguration.JdbcConfigurationBuilder()
                .username("docker")
                .password("mykenai1363")
                .driverClassName("org.postgresql.Driver")
                .jdbcUrl("jdbc:postgresql://localhost:5432/docker?characterEncoding=utf8")
                .build();

        HikariJdbcDataSource hikariJdbcDataSource = new HikariJdbcDataSource();
        return hikariJdbcDataSource.getDataSource(configuration);
    }
}
