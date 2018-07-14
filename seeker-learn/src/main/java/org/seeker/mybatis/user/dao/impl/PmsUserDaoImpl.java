package org.seeker.mybatis.user.dao.impl;

import org.seeker.common.base.mybatis.db.impl.BaseDaoImpl;
import org.seeker.mybatis.user.dao.PmsUserDao;
import org.seeker.mybatis.user.entity.PmsUser;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * @描述: 用户表数据访问层接口实现类.
 */
@Repository("pmsUserDao")
public class PmsUserDaoImpl extends BaseDaoImpl<PmsUser> implements PmsUserDao {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */

	public PmsUser findByUserNo(String userNo) {
		return super.getSqlSession().selectOne(getStatement("findByUserNo"), userNo);
	}

	public List<PmsUser> findAll() {
		return getSqlSession().selectList(getStatement("listAll"));
	}
	public static void main(String[] args) {
		String sqlId="findByUserNo";
		PmsUserDaoImpl daoImpl=new PmsUserDaoImpl();
		daoImpl.getStatement("findByUserNo");
	}
}
