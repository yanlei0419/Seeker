package org.seeker.sys.datadic.dao.jdbc;


import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.seeker.common.base.db.BaseDao;
import org.seeker.common.base.id.Uuid;
import org.seeker.sys.datadic.dao.DataDicTypeDao;
import org.seeker.sys.datadic.po.DataDicTypePo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典类别DaoImpl
 * 	自动生成
 * @Date 2015-03-09 17:08:51
 * @author yanlei
 */
public class DataDicTypeDaoImpl extends BaseDao implements DataDicTypeDao {
	
	/**
	 * 获取不分页列表
	 */
	public List<DataDicTypePo> getListNonPaged(DataDicTypePo po) throws SQLException {
		po.setBegin(-1);
		po.setEnd(-1);
		return getList(po);
	}
	/**
	 * 获取列表
	 */
	public List<DataDicTypePo> getList(DataDicTypePo po) throws SQLException {
		List paramList = new ArrayList();
		String sort = po.getSort()!=null&&!po.getSort().equals("") ? po.getSort() : "Id";//用于排序用的字段名
		String order = po.getOrder()!=null&&!po.getOrder().equals("") ? po.getOrder() : "desc";//排序方式，默认倒叙
		String sql =
				"    select "+
				"        row_number() over(order by t."+ sort +" "+ order +") rn,"+
				"        t.id,"+
				"        t.code,"+
				"        t.name,"+
				"        t.content,"+
				"        t.seq"+
				"    from sys_datadic_type t "+
				"    where 1=1 ";
		return super.executePageQuery(sql, paramList, new BeanListHandler(po.getClass()),po);
	}
	/**
	 * 获取记录数
	 */
	public int getCount(DataDicTypePo po) throws SQLException {
		List paramList = new ArrayList();
		String sql =
			"    select count(*) "+
			"    from sys_datadic_type t "+
			"    where 1=1 ";
		Object [] param = paramList.toArray();
		return super.executeCount(sql, param);
	}
	/**
	 * 获得DataDicTypePo
	 */
	public DataDicTypePo getDataDicType(DataDicTypePo po) throws SQLException {
		DataDicTypePo rtn = null;
		String sql = 
				"    select "+
				"        t.id,"+
				"        t.code,"+
				"        t.name,"+
				"        t.content,"+
				"        t.seq"+
				"    from sys_datadic_type t "+
				" where t.Id=? "; 
		rtn = (DataDicTypePo) super.executeQueryObject(sql, new Object []{po.getId()}, new BeanHandler(po.getClass()));
		return rtn;
	}
	/**
	 * 插入DataDicTypePo
	 */
	public int addDataDicType(DataDicTypePo po) throws SQLException {
		int rtn = 0;
		String sql =
				"insert into sys_datadic_type (" +
						" id ," +
						" code ," +
						" name ," +
						" content ," +
						" seq " +
				" ) values ( ? , ? , ? , ? , ? )";
		Object [] param = new Object [] { 
			new Uuid().getUUID(),
			po.getCode(),	
			po.getName(),	
			po.getContent(),	
			po.getSeq()	
		}; 
		try{
			rtn = super.executeUpdate(sql, param);
			throw new SQLException("模拟异常");
		}catch(SQLException e){
			
			throw new SQLException("模拟异常");
		}
	}
	/**
	 * 更新
	 */
	public int updateDataDicType(DataDicTypePo po) throws SQLException {
		int rtn = 0;
		String sql =
				"update sys_datadic_type set " +
						" code=?,"+
						" name=?,"+
						" content=?,"+
						" seq=?"+
				" where Id=?";	
		Object [] param = new Object [] { 
			po.getCode(),
			po.getName(),
			po.getContent(),
			po.getSeq(),
	
			po.getId()	
		}; 
		rtn = super.executeUpdate(sql, param);
		return rtn;
	}
	/**
	 * 删除
	 */
	public int deleteDataDicType(DataDicTypePo po) throws SQLException {
		int rtn = 0;
		String sql ="delete from sys_datadic_type where Id=?";
		Object [] param = new Object [] { 
			po.getId()
		};
		rtn = super.executeUpdate(sql, param);
		return rtn;
	}
	
}