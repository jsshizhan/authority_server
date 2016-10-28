package com.own.authority.crm.web.spring.security.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xiemeilong on 14-10-30.
 */
@Getter @Setter
public class LoginResult {
    private String message;
    private String taken;

    public LoginResult(String message, String taken) {
        this.message = message;
        this.taken = taken;
    }

    public LoginResult(String message) {
        this.message = message;
    }


}
