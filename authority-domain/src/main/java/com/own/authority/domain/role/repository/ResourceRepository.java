package com.own.authority.domain.role.repository;

import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.common.CrudRepository;
import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
public interface ResourceRepository extends CrudRepository<Resource,Long> {

    List<Resource> findAll(SystemType systemType, String searchField, int page, int size);

    List<Resource> findBySystemTypeAndLevel(SystemType systemType,int level);
}
