package com.own.authority.infrastructure.repository.mysql.query;

import com.own.authority.domain.role.model.Role;
import com.own.authority.infrastructure.repository.mysql.mapper.RoleMapper;
import com.own.authority.infrastructure.repository.mysql.model.RoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-09-07.
 */
@Repository
public class RoleQuery {

    @Autowired
    RoleMapper staffRoleMapper;

    public List<Role> query(RoleParam staffRoleParam){
        return staffRoleMapper.selectAll(staffRoleParam);
    }
}
