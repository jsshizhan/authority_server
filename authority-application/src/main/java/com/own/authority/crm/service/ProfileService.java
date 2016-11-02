package com.own.authority.crm.service;

import com.own.authority.crm.web.spring.CurrentUser;
import com.own.authority.crm.web.spring.security.User;
import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.repository.AccountRepository;
import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.RoleResourceRepository;
import com.own.authority.infrastructure.repository.mysql.query.ResourceQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 */
@Api("个人信息管理")
@RestController
@RequestMapping("/api/profile")
public class ProfileService {

    @Autowired
    AccountRepository staffAccountRepository;

    @Autowired
    ResourceQuery resourceQuery;

    @Autowired
    RoleResourceRepository roleResourceRepository;

    @ApiOperation(value = "个人信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Account getDetail(@CurrentUser User user) {
        return staffAccountRepository.find(user.getUsername());
    }


    @ApiOperation(value = "全部资源")
    @RequestMapping(value = "/whole/resource/{systemType}", method = RequestMethod.GET)
    public List<Resource> getWholeResouce(@CurrentUser User user, @PathVariable SystemType systemType) {
        return resourceQuery.findWholeResource(systemType);
    }

    @ApiOperation(value = "个人资源")
    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public List<Resource> getPersonResouce(@CurrentUser User user) {
        return resourceQuery.findPersonalResource(user.getRoleId(),SystemType.crm);
    }


    @ApiOperation(value = "个人资源ID")
    @RequestMapping(value = "/resource/{systemType}/{id}", method = RequestMethod.GET)
    public List<Long> getResouce(@CurrentUser User user,@PathVariable Long id,@PathVariable SystemType systemType) {
        return resourceQuery.findResourceId(id, systemType);
    }
}
