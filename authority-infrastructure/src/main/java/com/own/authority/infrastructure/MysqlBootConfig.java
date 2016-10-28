package com.own.authority.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by xiemeilong on 16-1-26.
 */
@Slf4j
@EnableTransactionManagement
@Configuration
@PropertySource(value = "classpath:persistence.properties")
public class MysqlBootConfig {
    @Autowired
    private DataSourceProperties properties;

    @Value("jdbc:mysql://${mysql/ip.port}/test?characterEncoding=UTF-8&useSSL=false")
    String mysqlUrl;

    @Value("${mysql/username}")
    String mysqlUserName;

    @Value("${mysql/password}")
    String mysqlPassword;

    @Bean
    @ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
    public DataSource dataSource() {
        log.debug("mysqlUrl:"+mysqlUrl);
        DataSourceBuilder factory = DataSourceBuilder
                .create(this.properties.getClassLoader())
                .driverClassName(this.properties.getDriverClassName())
                .url(mysqlUrl).username(mysqlUserName)
                .password(mysqlPassword);
        if (this.properties.getType() != null) {
            factory.type(this.properties.getType());
        }

        return factory.build();
    }

    /**
     * mybatis spring boot有个问题，倒置config file和mybatis.mapperLocations属性没法同时使用，所以才需要独立配置此bean
     * 等以后问题修复了，删掉这个bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("META-INF/mybatis-config.xml"));
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:META-INF/mybatis/**/*.xml"));

            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("parse mybatis xml is fail:" + e.getMessage());
        }
    }
}
