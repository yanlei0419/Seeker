package org.seeker.mybatis.user.dao;


import org.seeker.common.base.mybatis.db.BaseDao;
import org.seeker.mybatis.user.entity.PmsUser;

import java.util.List;

/**
 * 
 * @描述: 用户表数据访问层接口.
 */
public interface PmsUserDao extends BaseDao<PmsUser> {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */
	PmsUser findByUserNo(String userNo);
	List<PmsUser> findAll();

}
