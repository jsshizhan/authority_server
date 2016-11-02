package com.own.authority.infrastructure.repository.mysql.query;

import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.ResourceRepository;
import com.own.authority.infrastructure.repository.mysql.mapper.ResourceQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-09-05.
 */
@Repository
public class ResourceQuery {

    @Autowired
    ResourceQueryMapper resourceQueryMapper;

    @Autowired
    ResourceRepository resourceRepository;

    public List<Resource> findWholeResource(SystemType systemType){
        List<Resource> resources = resourceRepository.findBySystemTypeAndLevel(systemType,1);
        for(Resource resource : resources){
            List<Resource> resourceDb = resourceRepository.findByParentId(resource.getId());
            resource.setChildren(resourceDb);
        }
        return resources;
    }

    public List<Resource> findPersonalResource(Long roleId,SystemType systemType){
        List<Resource> resourcesParent = resourceRepository.findByRoleIdAndSystemTypeAndLevel(roleId,systemType,1);
        List<Resource> resourcesChildren = resourceRepository.findByRoleIdAndSystemTypeAndLevel(roleId,systemType,2);
        resourcesParent.forEach(resourceParent -> {
            resourcesChildren.forEach(resourceChildren -> {
                if(resourceParent.getId().equals(resourceChildren.getParentId())){
                    resourceParent.addChildren(resourceChildren);
                }
            });
        });
        return resourcesParent;
    }


    public List<Long> findResourceId(Long roleId, SystemType systemType){
        return resourceQueryMapper.selectIdByRoleIdAndSystemType(roleId,systemType);
    }
}
