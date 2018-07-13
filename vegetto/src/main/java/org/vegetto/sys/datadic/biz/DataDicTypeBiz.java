package org.vegetto.sys.datadic.biz;

import java.util.List;

import org.vegetto.sys.datadic.po.DataDicTypePo;

/**
 * 数据字典类别Biz
 * 	自动生成
 * @Date 2015-03-09 17:08:51
 */
public interface DataDicTypeBiz {
	List<DataDicTypePo> getListNonPaged(DataDicTypePo po);
	List<DataDicTypePo> getList(DataDicTypePo po);
	int getCount(DataDicTypePo po);
	DataDicTypePo getDataDicType(DataDicTypePo po);
	int addDataDicType(DataDicTypePo po);
	int updateDataDicType(DataDicTypePo po);
	int deleteDataDicType(DataDicTypePo[] pos);
	
}