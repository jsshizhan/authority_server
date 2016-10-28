package com.own.authority.domain.role.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Administrator on 2016-09-07.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResource {

    private Long id;

    private Long resourceId;

    private Long roleId;

    private Date createTime;


    public RoleResource(Long resourceId, Long roleId){
        this.resourceId = resourceId;
        this.roleId = roleId;
        this.createTime = new Date();
    }
}
