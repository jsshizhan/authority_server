package com.own.authority.infrastructure.repository.mysql.mapper;

import com.own.authority.domain.role.model.RoleResource;
import com.own.authority.domain.role.model.SystemType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-09-07.
 */
public interface RoleResourceMapper {

    void deleteByRoleIdAndSystemType(@Param("roleId") Long roleId, @Param("systemType") SystemType systemType);

    void insertBatch(List<RoleResource> list);

    void deleteByResourceId(@Param("resourceId") Long resourceId);
}
