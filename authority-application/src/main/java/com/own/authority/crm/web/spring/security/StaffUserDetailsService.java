package com.own.authority.crm.web.spring.security;

import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by xiemeilong on 14-10-25.
 */
@Service
public class StaffUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account staffAccount = accountRepository.find(username);
        if(staffAccount==null) {
            throw new UsernameNotFoundException("");
        }

        return new User(staffAccount);
    }
}
