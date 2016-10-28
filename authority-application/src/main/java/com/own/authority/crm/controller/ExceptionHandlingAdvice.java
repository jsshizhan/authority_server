package com.own.authority.crm.controller;

import com.own.authority.crm.Components;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.lang.model.UnknownEntityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;


/**
 * Created by xiemeilong on 14-11-25.
 */
@ControllerAdvice(basePackageClasses = {Components.class})
public class ExceptionHandlingAdvice {

    static Logger logger = LoggerFactory.getLogger(ExceptionHandlingAdvice.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)  //相关实体不存在
    @ExceptionHandler(UnknownEntityException.class)
    public void unknownEntityException() {}

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)  //业务限制不可执行（可通过其他方式解决）
    @ExceptionHandler(IllegalStateException.class)
    public @ResponseBody
    String illegalStateException(IllegalStateException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) //参数有问题
    @ExceptionHandler(InvalidParameterException.class)
    public @ResponseBody
    String invalidParameterException(InvalidParameterException e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    String serverError(HttpServletRequest request,HttpServletResponse response,Exception e) {
        logger.debug("server 500 : URL:{},detail:{}",request.getRequestURI(),e.getMessage());
        String s = "";
        for(StackTraceElement element : e.getStackTrace())
        {
            s+=element.toString() +"\r\n";
        }
        logger.error(s);
        return "网络错误";
    }

}
