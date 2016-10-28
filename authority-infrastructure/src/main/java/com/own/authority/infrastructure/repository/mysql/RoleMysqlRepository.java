package com.own.authority.infrastructure.repository.mysql;

import com.own.authority.domain.role.model.Role;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.RoleRepository;
import com.own.authority.infrastructure.repository.mysql.jpa.RoleJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
@Repository
public class RoleMysqlRepository implements RoleRepository{

    @Autowired
    RoleJPARepository roleJPARepository;

    @Override
    public void save(Role entity) {
        roleJPARepository.save(entity);
    }

    @Override
    public Role find(Long aLong) {
        return roleJPARepository.findOne(aLong);
    }

    @Override
    public void delete(Long aLong) {
        roleJPARepository.delete(aLong);
    }

    @Override
    public List<Role> findBySystemType(SystemType systemType) {
        return roleJPARepository.findBySystemType(systemType);
    }
}
