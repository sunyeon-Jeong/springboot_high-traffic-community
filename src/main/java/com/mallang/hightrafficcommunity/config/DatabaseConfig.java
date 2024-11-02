package com.mallang.hightrafficcommunity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// java 기반 설정파일, @Bean이 붙은 메서드의 반환객체를 스프링컨텍스트 빈 등록함
@Configuration
public class DatabaseConfig {

    // 데이터베이스 연결을 관리하는 객체
    @ConfigurationProperties(prefix = "spring.datasource") // application.properties DB 속성값 -> 자동으로 바인딩
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}