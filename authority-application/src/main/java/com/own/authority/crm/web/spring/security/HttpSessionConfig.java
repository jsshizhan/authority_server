package com.own.authority.crm.web.spring.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.ExpiringSession;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * Created by xiemeilong on 15-3-5.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Value("${services/redis/ip}")
    String redis_address;

    @Value("${services/redis/port}")
    Integer redis_port;


    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redis_address);
        factory.setPort(redis_port);
        return factory;
    }

    @Bean
    public RedisOperationsSessionRepository sessionRepository(RedisTemplate<String, ExpiringSession> sessionRedisTemplate) {
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(sessionRedisTemplate);
        sessionRepository.setDefaultMaxInactiveInterval(60*60*24*7);
        return sessionRepository;
    }


    //禁用session redis通知，性测时发现很耗性能,如果产生问题，删掉此段
    @Bean
    public InitializingBean enableRedisKeyspaceNotificationsInitializer(RedisConnectionFactory connectionFactory) {
        return new NoAction();
    }


    static class NoAction implements InitializingBean {
        @Override
        public void afterPropertiesSet() throws Exception {

        }
    }
}
