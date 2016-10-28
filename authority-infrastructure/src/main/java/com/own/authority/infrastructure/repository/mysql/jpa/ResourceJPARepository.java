package com.own.authority.infrastructure.repository.mysql.jpa;

import com.own.authority.domain.role.model.Resource;
import com.own.authority.domain.role.model.SystemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 */
@Repository
public interface ResourceJPARepository extends JpaRepository<Resource,Long> {

    List<Resource> findBySystemTypeAndLevel(SystemType systemType, int level);
}
