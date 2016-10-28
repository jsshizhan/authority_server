package com.own.authority.infrastructure.repository.mysql;

import com.own.authority.domain.role.model.RoleResource;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.RoleResourceRepository;
import com.own.authority.infrastructure.repository.mysql.mapper.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-09-07.
 */
@Repository
public class RoleResourceMysqlRepository implements RoleResourceRepository {

    @Autowired
    RoleResourceMapper roleResourceMapper;

    public void deleteByRoleIdAndSystemType(Long roleId, SystemType systemType){
        roleResourceMapper.deleteByRoleIdAndSystemType(roleId,systemType);
    }

    @Override
    public void insertBatch(List<RoleResource> roleResources) {
        roleResourceMapper.insertBatch(roleResources);
    }


}
