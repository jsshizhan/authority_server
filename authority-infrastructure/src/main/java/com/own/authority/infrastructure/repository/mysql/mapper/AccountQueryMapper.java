package com.own.authority.infrastructure.repository.mysql.mapper;

import com.own.authority.domain.role.model.SystemType;
import com.own.authority.infrastructure.repository.mysql.model.AccountView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-09-07.
 */
public interface AccountQueryMapper {

    List<AccountView> selectAll(@Param("systemType") SystemType systemType, @Param("roleId") Long roleId, @Param("searchField") String searchField, @Param("offset") int offset, @Param("size") int size);
}
