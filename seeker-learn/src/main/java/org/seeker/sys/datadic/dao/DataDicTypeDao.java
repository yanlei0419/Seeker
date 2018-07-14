package org.seeker.sys.datadic.dao;


import org.seeker.sys.datadic.po.DataDicTypePo;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据字典类别Dao
 * 	自动生成
 * @Date 2015-03-09 17:08:51
 */
public interface DataDicTypeDao {
	
	List<DataDicTypePo> getListNonPaged(DataDicTypePo po) throws SQLException;
	List<DataDicTypePo> getList(DataDicTypePo po) throws SQLException;
	int getCount(DataDicTypePo po) throws SQLException;
	DataDicTypePo getDataDicType(DataDicTypePo po) throws SQLException;
	int addDataDicType(DataDicTypePo po) throws SQLException;
	int updateDataDicType(DataDicTypePo po) throws SQLException;
	int deleteDataDicType(DataDicTypePo po) throws SQLException;
	
}