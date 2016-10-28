package com.own.authority.crm.web.spring.security;

import com.own.authority.domain.account.model.Account;
import com.own.authority.domain.account.model.AccountStateType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Administrator on 2016-03-28.
 */
@ToString
public class User implements UserDetails {

    static final long serialVersionUID = -2401990745633520434l;

    private String accountName;
    private String password;
    private boolean enabled;
    @Setter
    @Getter
    private Long staffAccountId;

    public User() {}


    public User(Account staffAccount){
        this.staffAccountId = staffAccount.getId();
        this.enabled = staffAccount.getState() != null && !staffAccount.getState().equals(AccountStateType.disabled);
        this.accountName = staffAccount.getAccountName();
        this.password = staffAccount.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
