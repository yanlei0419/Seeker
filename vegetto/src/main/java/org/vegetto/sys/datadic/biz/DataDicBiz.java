package org.vegetto.sys.datadic.biz;

import java.util.List;

import org.vegetto.sys.datadic.po.DataDicPo;

/**
 * 数据字典Biz
 * 	自动生成
 * @Date 2015-03-09 17:08:50
 */
public interface DataDicBiz {
	List<DataDicPo> getListNonPaged(DataDicPo po);
	List<DataDicPo> getList(DataDicPo po);
	int getCount(DataDicPo po);
	DataDicPo getDataDic(DataDicPo po);
	int addDataDic(DataDicPo po) throws Exception;
	int updateDataDic(DataDicPo po);
	int deleteDataDic(DataDicPo[] pos);
	
}