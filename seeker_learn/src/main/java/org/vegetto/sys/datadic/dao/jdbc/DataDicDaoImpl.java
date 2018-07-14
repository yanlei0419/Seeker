package org.vegetto.sys.datadic.dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import org.seeker.common.base.id.Uuid;
import org.seeker.common.base.db.BaseDao;
import org.seeker.common.util.StringUtils;
import org.vegetto.sys.datadic.dao.DataDicDao;
import org.vegetto.sys.datadic.po.DataDicPo;

/**
 * 数据字典DaoImpl
 * 	自动生成
 * @Date 2015-03-09 17:08:50
 * @author yanlei
 */
public class DataDicDaoImpl extends BaseDao implements DataDicDao {
	
	/**
	 * 获取不分页列表
	 */
	public List<DataDicPo> getListNonPaged(DataDicPo po) throws SQLException {
		po.setBegin(-1);
		po.setEnd(-1);
		return getList(po);
	}
	/**
	 * 获取列表
	 */
	public List<DataDicPo> getList(DataDicPo po) throws SQLException {
		List paramList = new ArrayList();
		String sort = po.getSort()!=null&&!po.getSort().equals("") ? po.getSort() : "Id";//用于排序用的字段名
		String order = po.getOrder()!=null&&!po.getOrder().equals("") ? po.getOrder() : "desc";//排序方式，默认倒叙
		String sql =
				"    select "+
				"        row_number() over(order by t."+ sort +" "+ order +") rn,"+
				"        t.id,"+
				"        t.typecode,"+
				"        t.code,"+
				"        t.name,"+
				"        t.content,"+
				"        t.isSystem,"+
				"        t.seq,"+
				"        t.isDelete,"+
				"        t.createBy,"+
				"        t.createTime,"+
				"        t.updateBy,"+
				"        t.updateTime,"+
				"        t1.name as typeName"+
				"    from sys_datadic t "+
				"    left join sys_datadic_type t1 on t1.code=t.typecode"+
				"    where 1=1 ";
			if(StringUtils.isNotNull(po.getTypecode())){
				sql += " and t.Typecode = ? ";
				paramList.add(po.getTypecode());
				//下面为模糊查询
				//sql += " and t.Typecode like '%"+ po.getTypecode() +"%' ";
			}
			if(StringUtils.isNotNull(po.getName())){
				sql += " and t.Name = ? ";
				paramList.add(po.getName());
				//下面为模糊查询
				//sql += " and t.Name like '%"+ po.getName() +"%' ";
			}
		return super.executePageQuery(sql, paramList, new BeanListHandler(po.getClass()),po);
	}
	/**
	 * 获取记录数
	 */
	public int getCount(DataDicPo po) throws SQLException {
		List paramList = new ArrayList();
		String sql =
			"    select count(*) "+
			"    from sys_datadic t "+
			"    where 1=1 ";
			if(StringUtils.isNotNull(po.getTypecode().toString().trim())){
				sql += " and t.Typecode = ? ";
				paramList.add(po.getTypecode());
				//下面为模糊查询
				//sql += " and t.Typecode like '%"+ po.getTypecode() +"%' ";
			}
			if(StringUtils.isNotNull(po.getName().toString().trim())){
				sql += " and t.Name = ? ";
				paramList.add(po.getName());
				//下面为模糊查询
				//sql += " and t.Name like '%"+ po.getName() +"%' ";
			}
		Object [] param = paramList.toArray();
		return super.executeCount(sql, param);
	}
	/**
	 * 获得DataDicPo
	 */
	public DataDicPo getDataDic(DataDicPo po) throws SQLException {
		DataDicPo rtn = null;
		String sql = 
				"    select "+
				"        t.id,"+
				"        t.typecode,"+
				"        t.code,"+
				"        t.name,"+
				"        t.content,"+
				"        t.isSystem,"+
				"        t.seq,"+
				"        t.isDelete,"+
				"        t.createBy,"+
				"        t.createTime,"+
				"        t.updateBy,"+
				"        t.updateTime,"+
				"        t1.name as typeName"+
				"    from sys_datadic t "+
				"    left join sys_datadic_type t1 on t1.code=t.typecode"+
				" where t.Id=? "; 
		rtn = (DataDicPo) super.executeQueryObject(sql, new Object []{po.getId()}, new BeanHandler(po.getClass()));
		return rtn;
	}
	/**
	 * 插入DataDicPo
	 */
	public int addDataDic(DataDicPo po) throws SQLException {
		int rtn = 0;
		String sql =
				"insert into sys_datadic (" +
						" id ," +
						" typecode ," +
						" code ," +
						" name ," +
						" content ," +
						" isSystem ," +
						" seq ," +
						" isDelete ," +
						" createBy ," +
						" createTime ," +
						" updateBy ," +
						" updateTime " +
				" ) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
		Object [] param = new Object [] { 
			new Uuid().getUUID(),	
			po.getTypecode(),	
			po.getCode(),	
			po.getName(),	
			po.getContent(),	
			po.getIsSystem(),	
			po.getSeq(),	
			po.getIsDelete(),	
			po.getCreateBy(),	
			po.getCreateTime(),	
			po.getUpdateBy(),	
			po.getUpdateTime()	
		}; 
		rtn = super.executeUpdate(sql, param);
		return rtn;
	}
	/**
	 * 更新
	 */
	public int updateDataDic(DataDicPo po) throws SQLException {
		int rtn = 0;
		String sql =
				"update sys_datadic set " +
						" typecode=?,"+
						" code=?,"+
						" name=?,"+
						" content=?,"+
						" isSystem=?,"+
						" seq=?,"+
						" isDelete=?,"+
						" createBy=?,"+
						" createTime=?,"+
						" updateBy=?,"+
						" updateTime=?"+
				" where Id=?";	
		Object [] param = new Object [] { 
			po.getTypecode(),
			po.getCode(),
			po.getName(),
			po.getContent(),
			po.getIsSystem(),
			po.getSeq(),
			po.getIsDelete(),
			po.getCreateBy(),
			po.getCreateTime(),
			po.getUpdateBy(),
			po.getUpdateTime(),
	
			po.getId()	
		}; 
		rtn = super.executeUpdate(sql, param);
		return rtn;
	}
	/**
	 * 删除
	 */
	public int deleteDataDic(DataDicPo po) throws SQLException {
		int rtn = 0;
		String sql ="delete from sys_datadic where Id=?";
		Object [] param = new Object [] { 
			po.getId()
		};
		rtn = super.executeUpdate(sql, param);
		return rtn;
	}
	
}