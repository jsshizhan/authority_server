package com.own.authority.crm.service;

import com.own.authority.crm.model.RevisePassword;
import com.own.authority.crm.web.spring.CurrentUser;
import com.own.authority.crm.web.spring.security.User;
import com.own.authority.domain.account.AccountService;
import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.model.AccountStateType;
import com.own.authority.domain.account.repository.AccountRepository;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.infrastructure.repository.mysql.model.AccountView;
import com.own.authority.infrastructure.repository.mysql.query.AccountQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2016-08-31.
 */
@Api("账户管理")
@RestController
@RequestMapping("/api/account")
public class AccountManagerService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountQuery accountQuery;

    @Autowired
    AccountService staffAccountService;

    @ApiOperation(value = "帐号管理")
    @RequestMapping(value = "", method = RequestMethod.GET)
    List<AccountView> find(@CurrentUser User user, @RequestParam SystemType systemType, @RequestParam(required = false) Long roleId,
                           @RequestParam(required = false) String searchField,
                           @RequestParam(required = false, defaultValue = "1") int page,
                           @RequestParam(required = false, defaultValue = "10") int size) {
        return accountQuery.find(systemType,roleId,searchField,page,size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Account find(@CurrentUser User user, @PathVariable Long id) {
        return accountRepository.find(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@CurrentUser User user, @PathVariable Long id) {
        accountRepository.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    void update(@CurrentUser User user, @PathVariable Long id,@RequestBody Account staff) {
        Account dbStaff = accountRepository.find(id);
        dbStaff.assign(staff);
        accountRepository.save(dbStaff);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    void signUp(@CurrentUser User user, @RequestBody Account record) {
        Account staffAccount = accountRepository.find(record.getAccountName());
        if (staffAccount != null) {
            throw new IllegalStateException("用戶已存在！");
        }
        record.setPassword("yuanmai");
        record.setState(AccountStateType.normal);
        staffAccountService.encoderPassword(record);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    void change(@CurrentUser User user, @RequestBody RevisePassword revisePassword) {
        Account staffAccount = accountRepository.find(user.getStaffAccountId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(revisePassword.getOldPassword(), staffAccount.getPassword())) {
            staffAccount.setPassword(revisePassword.getNewPassword());
            staffAccountService.encoderPassword(staffAccount);
        }
        else{
            throw new IllegalStateException("修改密码失败！");
        }
    }

    @RequestMapping(value = "/{id}/defaultPassword", method = RequestMethod.POST)
    void change(@CurrentUser User user, @PathVariable Long id) {
        Account staffAccount = accountRepository.find(id);
        if (staffAccount != null) {
            staffAccount.setPassword("yuanmai");
            staffAccountService.encoderPassword(staffAccount);
        }
    }


}
