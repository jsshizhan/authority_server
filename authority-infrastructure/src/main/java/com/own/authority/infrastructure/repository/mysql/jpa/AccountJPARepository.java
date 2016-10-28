package com.own.authority.infrastructure.repository.mysql.jpa;

import com.own.authority.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
@Repository
public interface AccountJPARepository extends JpaRepository<Account,Long> {

    Account findByAccountName(String accountName);

    List<Account> findByRoleId(Long roleId);
}
