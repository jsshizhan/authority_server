package com.own.authority.domain.account;

import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-15.
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void encoderPassword(Account staffAccount){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        staffAccount.setPassword(passwordEncoder.encode(staffAccount.getPassword()));
        accountRepository.save(staffAccount);
    }
}
