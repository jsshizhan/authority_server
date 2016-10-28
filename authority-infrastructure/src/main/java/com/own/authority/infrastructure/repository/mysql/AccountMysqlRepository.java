package com.own.authority.infrastructure.repository.mysql;

import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.repository.AccountRepository;
import com.own.authority.infrastructure.repository.mysql.jpa.AccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
@Repository
public class AccountMysqlRepository implements AccountRepository{
    @Autowired
    AccountJPARepository accountJPARepository;

    @Override
    public void save(Account entity) {
        accountJPARepository.save(entity);
    }

    @Override
    public Account find(Long aLong) {
        return accountJPARepository.findOne(aLong);
    }

    @Override
    public void delete(Long aLong) {
        accountJPARepository.delete(aLong);
    }

    @Override
    public Account find(String accountName) {
        return accountJPARepository.findByAccountName(accountName);
    }

    @Override
    public List<Account> findByRoleId(Long roleId) {
        return accountJPARepository.findByRoleId(roleId);
    }
}
