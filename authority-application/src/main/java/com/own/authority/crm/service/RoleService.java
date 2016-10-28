package com.own.authority.crm.service;

import com.own.authority.crm.web.spring.CurrentUser;
import com.own.authority.crm.web.spring.security.User;
import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.repository.AccountRepository;
import com.own.authority.domain.role.model.Role;
import com.own.authority.domain.role.model.RoleResource;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.RoleRepository;
import com.own.authority.domain.role.repository.RoleResourceRepository;
import com.own.authority.infrastructure.repository.mysql.model.RoleParam;
import com.own.authority.infrastructure.repository.mysql.query.RoleQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-09-07.
 */
@Api("角色管理")
@RestController
@RequestMapping("/api/staffrole")
public class RoleService {

    @Autowired
    RoleRepository staffRoleRepository;

    @Autowired
    RoleQuery staffRoleQuery;

    @Autowired
    RoleResourceRepository roleResourceRepository;

    @Autowired
    AccountRepository staffAccountRepository;

    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    List<Role> find(@CurrentUser User user, @ModelAttribute RoleParam staffRoleParam) {
        return staffRoleQuery.query(staffRoleParam);
    }

    @ApiOperation(value = "角色列表(前端下拉选项)")
    @RequestMapping(value = "/map/{systemtype}", method = RequestMethod.GET)
    Map<String,Object> map(@CurrentUser User user,@PathVariable("systemtype") SystemType systemType) {
        Map<String,Object> map = new HashMap<>();
        List<Role> list = staffRoleRepository.findBySystemType(systemType);
        map.put("results",list);
        map.put("success",true);
        return map;
    }


    @ApiOperation(value = "角色修改")
    @Transactional
    @RequestMapping(value = "/{roleId}", method = RequestMethod.POST)
    void update(@CurrentUser User user, @RequestParam String roleName, @RequestParam SystemType systemType, @RequestParam String description, @RequestBody(required = false) List<Long> resourceIds, @PathVariable Long roleId) {
        Role staffRole = new Role(roleId,roleName,description,systemType);
        staffRoleRepository.save(staffRole);
        if(resourceIds!=null && resourceIds.size() > 0) {
            roleResourceRepository.deleteByRoleIdAndSystemType(roleId, systemType);
            List<RoleResource> roleResources = new ArrayList<>();
            resourceIds.forEach(resourceId -> {
                RoleResource roleResource = new RoleResource(resourceId, roleId);
                roleResources.add(roleResource);
            });
            roleResourceRepository.insertBatch(roleResources);
        }
    }

    @ApiOperation(value = "角色添加")
    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    void add(@CurrentUser User user, @RequestParam String roleName,@RequestParam String description,@RequestParam SystemType systemType,@RequestBody(required = false) List<Long> resourceIds) {
        Role staffRole = new Role(roleName,description,systemType);
        staffRoleRepository.save(staffRole);
        if(resourceIds!=null && resourceIds.size() > 0) {
            List<RoleResource> roleResources = new ArrayList<>();
            resourceIds.forEach(resourceId -> {
                RoleResource roleResource = new RoleResource(resourceId, staffRole.getId());
                roleResources.add(roleResource);
            });
            roleResourceRepository.insertBatch(roleResources);
        }
    }


    @ApiOperation(value = "角色删除")
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void save(@CurrentUser User user, @PathVariable Long id) {
        List<Account> staffAccounts = staffAccountRepository.findByRoleId(id);
        if(staffAccounts != null && staffAccounts.size() > 0){
            throw new IllegalStateException("此角色下存在用户！");
        }

        Role staffRole = staffRoleRepository.find(id);
        roleResourceRepository.deleteByRoleIdAndSystemType(staffRole.getId(), SystemType.crm);
        staffRoleRepository.delete(id);
    }

}
