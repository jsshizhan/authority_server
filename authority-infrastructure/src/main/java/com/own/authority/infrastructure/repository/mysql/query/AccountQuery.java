package com.own.authority.infrastructure.repository.mysql.query;

import com.own.authority.domain.role.model.SystemType;
import com.own.authority.infrastructure.repository.mysql.mapper.AccountQueryMapper;
import com.own.authority.infrastructure.repository.mysql.model.AccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-09-07.
 */
@Repository
public class AccountQuery {

    @Autowired
    AccountQueryMapper accountQueryMapper;

    public List<AccountView> find(SystemType systemType, Long roleId, String searchField, int page, int size){
        return accountQueryMapper.selectAll(systemType,roleId,searchField,(page-1)*size,size);
    }

}
