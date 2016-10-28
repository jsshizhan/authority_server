package com.own.authority.domain.common;

/**
 * Created by xiemeilong on 15-8-10.
 */
public interface CaptchaValidator {
    boolean isAfter(int minutes);

    boolean validate(String captcha);

    String getCaptcha();

    void setCaptcha(String value);

    void makeExpire();
}
