package com.own.authority.crm.controller.propertyeditor;


import java.beans.PropertyEditorSupport;

/**
 * Created by Administrator on 2016-09-08.
 */
public class SystemTypePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        try {
            setValue(text);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Custom binding failed. Input type: String. Expected type of value to be set: X", e);
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
