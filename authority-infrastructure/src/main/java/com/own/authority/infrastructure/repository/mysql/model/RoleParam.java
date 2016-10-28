package com.own.authority.infrastructure.repository.mysql.model;
import com.own.authority.domain.role.model.SystemType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016-09-07.
 */
@Getter
@Setter
public class RoleParam extends PageParameter {

    private SystemType systemType;

    private String searchField;
}
