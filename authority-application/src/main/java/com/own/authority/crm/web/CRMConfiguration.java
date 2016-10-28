package com.own.authority.crm.web;

import com.own.authority.infrastructure.InfrastructureConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrator on 2016-03-01.
 */
@Configuration
@Import({InfrastructureConfiguration.class})
public class CRMConfiguration {

}
