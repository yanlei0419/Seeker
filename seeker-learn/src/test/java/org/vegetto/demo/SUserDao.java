package org.vegetto.demo;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.seeker.common.base.db.BaseDao;
import org.vegetto.entity.SUser;

@SuppressWarnings("unchecked")
public class SUserDao extends BaseDao {
	
	public List getList() throws SQLException{
		String sql = "select * from sys_user ";
		Object[] param = new Object[] {};
		ResultSetHandler<SUser> listh=new BeanListHandler(SUser.class);
		List<SUser> list=(List<SUser>)super.executeQuery(sql,param,listh);
		return list;
	}
	
	
	public SUser getEntity() throws SQLException{
		Object[] param = new Object[] {};
		String sql = "select * from sys_user where id= '1' ";
		ResultSetHandler beanh=new BeanHandler(SUser.class);
		SUser s=(SUser)super.executeQueryByObject(sql,param,beanh);
		return s;
	}
}
