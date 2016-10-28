package com.own.authority.crm.service;

import com.own.authority.crm.web.spring.CurrentUser;
import com.own.authority.crm.web.spring.security.User;
import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.ResourceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-09-09.
 */
@Api("资源管理")
@RestController
@RequestMapping("/api/resource")
public class ResourceManageService {

    @Autowired
    ResourceRepository resourceRepository;

    @ApiOperation(value = "资源列表(下拉选项)")
    @RequestMapping(value = "/map/{systemtype}", method = RequestMethod.GET)
    Map<String,Object> findParentMap(@CurrentUser User user, @PathVariable SystemType systemtype) {
        Map<String,Object> map = new HashMap<>();
        List<Resource> list = resourceRepository.findBySystemTypeAndLevel(systemtype,1);
        map.put("results",list);
        map.put("success",true);
        return map;
    }

    @ApiOperation(value = "资源详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Resource get(@CurrentUser User user, @PathVariable Long id) {
        return resourceRepository.find(id);
    }

    @ApiOperation(value = "资源列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    List<Resource> find(@CurrentUser User user, @RequestParam(required = false) SystemType systemType,
                        @RequestParam(required = false) String searchField,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
        return resourceRepository.findAll(systemType,searchField,page,size);
    }

    @ApiOperation(value = "资源删除")
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@CurrentUser User user, @PathVariable Long id) {
        resourceRepository.delete(id);
    }

    @ApiOperation(value = "资源添加")
    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    void save(@CurrentUser User user, @RequestBody Resource resource) {
        if(resource.getLevel() == 2){
            resource.setLink(true);
        }
        else{
            resource.setLink(false);
        }
        resource.setCreateTime(new Date());
        resourceRepository.save(resource);
    }

    @ApiOperation(value = "资源修改")
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    void update(@CurrentUser User user,@PathVariable Long id, @RequestBody Resource resource) {
        Resource resourceDb = resourceRepository.find(id);
        resourceDb.assign(resource);
        resourceRepository.save(resourceDb);
    }


}
