package com.own.authority.infrastructure.repository.mysql;

import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.ResourceRepository;
import com.own.authority.infrastructure.repository.mysql.jpa.ResourceJPARepository;
import com.own.authority.infrastructure.repository.mysql.mapper.ResourceMapper;
import com.own.authority.infrastructure.repository.mysql.mapper.ResourceQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
@Repository
public class ResourceMysqlRepository implements ResourceRepository{

    @Autowired
    ResourceJPARepository resourceJPARepository;

    @Autowired
    ResourceQueryMapper resourceQueryMapper;

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public void save(Resource entity) {
        resourceJPARepository.save(entity);
    }

    @Override
    public Resource find(Long aLong) {
        return resourceJPARepository.findOne(aLong);
    }

    @Override
    public void delete(Long aLong) {
        resourceJPARepository.delete(aLong);
    }

    @Override
    public List<Resource> findAll(SystemType systemType, String searchField, int page, int size) {
        return resourceQueryMapper.selectAll(systemType,searchField,(page-1)*size,size);
    }

    @Override
    public List<Resource> findBySystemTypeAndLevel(SystemType systemType, int level) {
        return resourceMapper.selectBySystemTypeAndLevel(systemType,level);
    }

    @Override
    public List<Resource> findByParentId(Long id) {
        return resourceMapper.selectByParentId(id);
    }

    @Override
    public List<String> findBySystemTypeAndAccountId(SystemType systemType, Long accountId) {
        return resourceMapper.selectBySystemTypeAndAccountId(systemType,accountId);
    }

    @Override
    public List<Resource> findByRoleIdAndSystemTypeAndLevel(Long roleId, SystemType systemType, int level) {
        return resourceMapper.selectByRoleIdAndSystemTypeAndLevel(roleId,systemType,level);
    }
}
