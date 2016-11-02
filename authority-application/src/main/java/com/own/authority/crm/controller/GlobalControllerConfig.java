package com.own.authority.crm.controller;

import com.own.authority.crm.controller.propertyeditor.SystemTypePropertyEditor;
import com.own.authority.domain.role.model.SystemType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Administrator on 2016-09-08.
 */
@ControllerAdvice
public class GlobalControllerConfig {

    @InitBinder
    public void registerCustomEditors(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(SystemType.class, new SystemTypePropertyEditor());
    }
}
