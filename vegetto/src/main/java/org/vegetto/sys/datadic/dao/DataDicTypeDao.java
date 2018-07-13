package org.vegetto.sys.datadic.dao;

import java.util.List;
import java.sql.SQLException;

import org.vegetto.sys.datadic.po.DataDicTypePo;

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