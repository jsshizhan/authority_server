package com.own.authority.infrastructure.repository.mysql.mapper;

import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-09-05.
 */
public interface ResourceQueryMapper {

    List<Long> selectIdByRoleIdAndSystemType(@Param("roleId") Long roleId, @Param("systemType") SystemType systemType);


    List<Resource> selectAll(@Param("systemType") SystemType systemType, @Param("searchField") String searchField, @Param("offset") int offset, @Param("size") int size);
}
