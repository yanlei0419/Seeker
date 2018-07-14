package org.seeker.sys.datadic.biz;


import org.seeker.sys.datadic.po.DataDicPo;

import java.util.List;

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