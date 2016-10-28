package com.own.authority.domain.account.repository;

import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.common.CrudRepository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
public interface AccountRepository extends CrudRepository<Account,Long>{

    Account find(String accountName);
    List<Account> findByRoleId(Long roleId);
}
