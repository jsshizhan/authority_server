package com.own.authority.crm.web.spring.security;

import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.model.AccountStateType;
import com.own.authority.domain.role.model.SystemType;
import com.own.authority.domain.role.repository.ResourceRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016-03-28.
 */
@ToString
@Component
public class User implements UserDetails {

    static ResourceRepository resourceRepository;

    @Autowired
    public User(ResourceRepository resourceRepository){
        this.resourceRepository = resourceRepository;
    }


    static final long serialVersionUID = -2401990745633520434l;

    private String accountName;
    private String password;
    private boolean enabled;
    @Setter
    @Getter
    private Long staffAccountId;

    @Setter
    @Getter
    private Long roleId;

    public User() {}


    public User(Account staffAccount){
        this.staffAccountId = staffAccount.getId();
        this.enabled = staffAccount.getState() != null && !staffAccount.getState().equals(AccountStateType.disabled);
        this.accountName = staffAccount.getAccountName();
        this.password = staffAccount.getPassword();
        this.roleId = staffAccount.getRoleId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = getGrantedAuthorities();
        return authList;
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        List<String> resources = resourceRepository.findBySystemTypeAndAccountId(SystemType.crm,this.staffAccountId);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : resources) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return accountName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
