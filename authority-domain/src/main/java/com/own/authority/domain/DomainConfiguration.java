package com.own.authority.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by xiemeilong on 2014-10-23.
 */
@Configuration
@ComponentScan
public class DomainConfiguration {

    @Bean
    TemplateFormatter templateFormatter() {
        return new TemplateFormatter("/template");
    }

}
