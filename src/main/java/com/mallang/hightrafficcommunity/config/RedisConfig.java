package com.mallang.hightrafficcommunity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration // spring 설정클래스 + Bean 주입
public class RedisConfig {

    // @Value를 통해 application.properties 설정값 주입
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Value("${expire.defaultTime}")
    private Long defaultTime;

    /* RedisCacheManager 직렬화 */
    // Redis에 객체를 json 형태로 저장
    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();

        // timestamp 비활성화 -> ISO-8601 형식으로 저장
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // json 직렬화
        objectMapper.registerModule(new Jdk8Module());

        return objectMapper;

    }

    /* Redis 서버와의 연결을 설정하고 관리함 */
    // 매번 새로운 redis 연결을 생성하지 않고 재사용하여 성능을 높임 ; factory 캡슐화
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        // 연결정보
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword);

        // Redis의 Java 클라이언트(구현체) : Redis 서버와 Java 어플리케이션 연결 관리, 명령 호출
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;

    }

    /* Redis를 캐시 저장소로 사용하여 캐시데이터 관리 */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory,
                                                                                ObjectMapper objectMapper) {

        // 캐시된 객체 -> Redis에 저장하기 위해 직렬화(객체를 json형태로 변환)
        RedisCacheConfiguration redisConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues() // null값 캐시에 저장 X
                .entryTtl(Duration.ofSeconds(defaultTime)) // 캐시 기본 만료시간
                .serializeKeysWith(RedisSerializationContext // 캐시 key 문자열로 직렬화, json 형식으로 직렬화
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer())).serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)));

        // redisConnectionFactory를 기반으로 RedisCacheManager 빌드
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisConfiguration).build();

    }

}