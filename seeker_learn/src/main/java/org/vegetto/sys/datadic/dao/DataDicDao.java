package org.vegetto.sys.datadic.dao;

import java.util.List;
import java.sql.SQLException;

import org.vegetto.sys.datadic.po.DataDicPo;

/**
 * 数据字典Dao
 * 	自动生成
 * @Date 2015-03-09 17:08:50
 */
public interface DataDicDao {
	
	List<DataDicPo> getListNonPaged(DataDicPo po) throws SQLException;
	List<DataDicPo> getList(DataDicPo po) throws SQLException;
	int getCount(DataDicPo po) throws SQLException;
	DataDicPo getDataDic(DataDicPo po) throws SQLException;
	int addDataDic(DataDicPo po) throws SQLException;
	int updateDataDic(DataDicPo po) throws SQLException;
	int deleteDataDic(DataDicPo po) throws SQLException;
	
}