package com.own.authority.infrastructure.repository.mysql.model;

import com.own.authority.domain.account.model.AccountStateType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016-09-07.
 */
@Getter
@Setter
public class AccountView {

    private Long id;

    private String realName;

    private String accountName;

    private String phone;

    private AccountStateType state;

    private Long roleId;

    private String roleName;
}
