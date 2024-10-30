package com.mallang.hightrafficcommunity.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

// Mybatis <-> Spring 통합설정
@Configuration // spring 설정클래스 + Bean 주입
@MapperScan(basePackages = "com.mallang.hightrafficcommunity.mapper")
public class MysqlConfig {

    // Mybatis가 데이터베이스와의 연결을 관리
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        // Mybatis와 Sprin 통합 FactoryBean
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // Mybatis -> 데이터베이스 연결
        sqlSessionFactoryBean.setDataSource(dataSource);

        // mapper 패키지 내 모든 xml파일 -> mapper로 사용 설정
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations((resolver.getResource("classpath:mapper/*.xml")));

        // mybatis-config.xml 파일 읽기 -> Mybatis 설정에 적용
        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(myBatisConfig);

        // 최종 설정 완료된 sqlSessionFactoryBean 객체 반환 -> Spring 컨텍스트에 등록
        return sqlSessionFactoryBean.getObject();

    }

}