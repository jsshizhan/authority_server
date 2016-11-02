package com.own.authority.domain.role.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
@Getter
@Setter
@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    private String name;

    private String value;

    private Date createTime;

    private int level;

    private int orderBy;

    private boolean isLink;

    @Transient
    private List<Resource> children;

    @Enumerated(EnumType.STRING)
    private SystemType systemType;

    public void assign(Resource resource){
        this.parentId = resource.getParentId();
        this.name = resource.getName();
        this.value = resource.getValue();
        this.orderBy = resource.getOrderBy();
        this.level = resource.getLevel();
    }

    public void addChildren(Resource resource){
        if(this.children == null){
            children = new ArrayList<>();
        }
        children.add(resource);
    }
}
