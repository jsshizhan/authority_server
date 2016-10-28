package com.own.authority.crm.web.spring.security.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xiemeilong on 14-12-23.
 */
@Getter
@Setter
public class LoginInput {
    String username;
    String password;
    String captcha;
}
