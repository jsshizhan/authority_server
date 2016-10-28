package com.own.authority.domain.account.model;

import com.own.authority.domain.role.repository.RoleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * Created by Administrator on 2016-10-27.
 */
@Getter
@Setter
@Entity
public class Account {

    static RoleRepository staffRoleRepository;

    @Autowired
    public Account(RoleRepository staffRoleRepository){
        this.staffRoleRepository = staffRoleRepository;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;

    private String realName;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStateType state;

    private String phone;

    private String avatar;

    private Long roleId;

    public void assign(Account staffAccount){
        this.accountName = staffAccount.getAccountName();
        this.realName = staffAccount.getRealName();
        this.state = staffAccount.getState();
        this.phone = staffAccount.getPhone();
        this.roleId = staffAccount.getRoleId();
    }

}
