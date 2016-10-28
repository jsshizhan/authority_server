package com.own.authority.domain.common;

/**
 * 仓库基类
 */
public interface CrudRepository<T, ID> {

    /**
     * 保存实体（添加或更新）
     *
     * @param entity
     */
    public void save(T entity);

    /**
     * 根据id获取实体
     *
     * @param id
     * @return
     */
    public T find(ID id);

    /**
     * 物理删除实体
     *
     * @param id
     */
    public void delete(ID id);

}