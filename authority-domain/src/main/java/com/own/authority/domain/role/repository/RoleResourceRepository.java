package com.own.authority.domain.role.repository;


import com.own.authority.domain.role.model.RoleResource;
import com.own.authority.domain.role.model.SystemType;

import java.util.List;

/**
 * Created by Administrator on 2016-09-07.
 */
public interface RoleResourceRepository {

    void deleteByRoleIdAndSystemType(Long roleId, SystemType systemType);

    void insertBatch(List<RoleResource> roleResources);

}
