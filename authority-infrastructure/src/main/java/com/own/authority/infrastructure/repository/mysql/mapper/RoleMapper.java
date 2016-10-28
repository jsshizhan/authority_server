package com.own.authority.infrastructure.repository.mysql.mapper;

import com.own.authority.domain.role.model.Role;
import com.own.authority.infrastructure.repository.mysql.model.RoleParam;

import java.util.List;

/**
 * Created by Administrator on 2016-09-07.
 */
public interface RoleMapper {

    List<Role> selectAll(RoleParam staffRoleParam);

    Role selectByAccountId(Long accountId);
}
