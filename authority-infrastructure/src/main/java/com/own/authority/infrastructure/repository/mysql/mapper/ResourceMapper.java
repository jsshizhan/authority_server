package com.own.authority.infrastructure.repository.mysql.mapper;

import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-11-02.
 */
public interface ResourceMapper {

    List<Resource> selectByParentId(@Param("parentId") Long parentId);

    List<Resource> selectBySystemTypeAndLevel(@Param("systemType")SystemType systemType, @Param("level") int level);

    List<Resource> selectByRoleIdAndSystemTypeAndLevel(@Param("roleId") Long roleId, @Param("systemType")SystemType systemType, @Param("level") int level);

    List<String> selectBySystemTypeAndAccountId(@Param("systemType") SystemType systemType, @Param("accountId") Long accountId);
}
