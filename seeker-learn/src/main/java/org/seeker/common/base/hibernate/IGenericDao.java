package org.seeker.common.base.hibernate;

import java.io.Serializable;
import java.util.List;

import org.seeker.common.base.hibernate.entity.PageBean;

// T 代表待操作的实体类型
// ID 代表待操作的实体类主键类型
public interface IGenericDao<T extends Serializable, ID extends Serializable> {
	
	// 添加
	public abstract void insert(final T entity);
	// 查找
	public abstract T selectById(final ID id);
	
	public abstract List<T> findByCondition(String conditionName,Object conditionValue);
	
	public abstract List<T> selectAll();
	// 更新
	public abstract void update(final T entity);	
	// 删除
	public abstract void delete(final T entity);
	// 分页显示
	public abstract PageBean selectByPage(final String strHQL, final int currentPage, final int pageSize, final Object...params);
	
}
