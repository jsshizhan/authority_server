package com.own.authority.domain.role.repository;

import com.own.authority.domain.common.CrudRepository;
import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.Role;
import com.own.authority.domain.role.model.SystemType;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
public interface RoleRepository extends CrudRepository<Role,Long> {

    List<Role> findBySystemType(SystemType systemType);
}
