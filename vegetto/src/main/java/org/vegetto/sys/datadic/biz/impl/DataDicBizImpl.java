package org.vegetto.sys.datadic.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.vegetto.common.base.db.DBConnection;
import org.vegetto.sys.datadic.biz.DataDicBiz;
import org.vegetto.sys.datadic.dao.DataDicDao;
import org.vegetto.sys.datadic.dao.DataDicTypeDao;
import org.vegetto.sys.datadic.po.DataDicPo;
import org.vegetto.sys.datadic.po.DataDicTypePo;


/**
 * 数据字典BizImpl
 * 	自动生成
 * @Date 2015-03-09 17:08:50
 */
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DataDicBizImpl implements DataDicBiz {

	private DataDicDao dao;
	@Resource(name="DataDicTypeDao")
	private DataDicTypeDao typeDao;
	
	public void setTypeDao(DataDicTypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void setDao(DataDicDao dao){
		this.dao = dao;
	}

	public List<DataDicPo> getListNonPaged(DataDicPo po) {
		List<DataDicPo> rtn = new ArrayList<DataDicPo>();
		try {
			rtn = dao.getListNonPaged(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	public List<DataDicPo> getList(DataDicPo po) {
		List<DataDicPo> rtn = new ArrayList<DataDicPo>();
		try {
			rtn = dao.getList(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	public int getCount(DataDicPo po) {
		int rtn = 0;
		try {
			rtn = dao.getCount(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	public DataDicPo getDataDic(DataDicPo po) {
		DataDicPo rtn = new DataDicPo();
		try {
			DBConnection.beginTransaction();
			rtn = dao.getDataDic(po);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			e.printStackTrace();
		}
		return rtn;
	}
	public int addDataDic(DataDicPo po) throws Exception {
		int rtn = 0;
		try {
			rtn = dao.addDataDic(po);
			DataDicTypePo typePo=new DataDicTypePo();
			typePo.setName("测试type");
			typePo.setCode("1");
			this.typeDao.addDataDicType(typePo);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return rtn;
	}
	public int updateDataDic(DataDicPo po) {
		int rtn = 0;
		try {
			DBConnection.beginTransaction();
			rtn = dao.updateDataDic(po);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			e.printStackTrace();
		}
		return rtn;
	}
	public int deleteDataDic(DataDicPo[] pos) {
		int rtn = 0;
		try {
			DBConnection.beginTransaction();
			for (DataDicPo po : pos) {
				dao.deleteDataDic(po);
				rtn++;
			}
			DBConnection.commitTransaction();
		} catch (Exception e) {
			rtn = 0;
			DBConnection.rollbackTransaction();
			e.printStackTrace();
		}
		return rtn;
	}
	
}