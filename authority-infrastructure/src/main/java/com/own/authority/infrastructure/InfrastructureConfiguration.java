package com.own.authority.infrastructure;

import com.own.authority.domain.DomainConfiguration;
import com.own.authority.infrastructure.repository.mysql.mapper.Components;
import com.yuanmai.logistics.boot.redis.RedisConfig;
import com.yuanmai.logistics.sdk.integration.discovery.LogisticsDiscovery;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by xiemeilong on 15-12-26.
 */
@Slf4j
@Configuration
@EntityScan(basePackageClasses = {com.own.authority.domain.Components.class})
@EnableJpaRepositories(basePackageClasses = {com.own.authority.infrastructure.repository.mysql.jpa.Components.class})
@Import({DomainConfiguration.class,LogisticsDiscovery.class,RedisConfig.class})
@ComponentScan
@MapperScan(basePackageClasses = {Components.class})
public class InfrastructureConfiguration {
}
