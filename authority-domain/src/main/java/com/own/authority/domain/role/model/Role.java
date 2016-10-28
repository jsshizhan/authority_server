package com.own.authority.domain.role.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Administrator on 2016-10-27.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    public Role(String roleName,String description,SystemType systemType){
        this.roleName = roleName;
        this.description = description;
        this.systemType = systemType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    private String description;

    @Enumerated(EnumType.STRING)
    private SystemType systemType;



}
