package org.seeker.sys.datadic.biz.impl;


import org.seeker.common.base.db.DBConnection;
import org.seeker.sys.datadic.biz.DataDicTypeBiz;
import org.seeker.sys.datadic.dao.DataDicTypeDao;
import org.seeker.sys.datadic.po.DataDicTypePo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典类别BizImpl
 * 	自动生成
 * @Date 2015-03-09 17:08:51
 */
public class DataDicTypeBizImpl implements DataDicTypeBiz {

	private DataDicTypeDao dao;
	public void setDao(DataDicTypeDao dao){
		this.dao = dao;
	}

	public List<DataDicTypePo> getListNonPaged(DataDicTypePo po) {
		List<DataDicTypePo> rtn = new ArrayList<DataDicTypePo>();
		try {
			rtn = dao.getListNonPaged(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	public List<DataDicTypePo> getList(DataDicTypePo po) {
		List<DataDicTypePo> rtn = new ArrayList<DataDicTypePo>();
		try {
			rtn = dao.getList(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	public int getCount(DataDicTypePo po) {
		int rtn = 0;
		try {
			rtn = dao.getCount(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	public DataDicTypePo getDataDicType(DataDicTypePo po) {
		DataDicTypePo rtn = new DataDicTypePo();
		try {
			DBConnection.beginTransaction();
			rtn = dao.getDataDicType(po);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			e.printStackTrace();
		}
		return rtn;
	}
	public int addDataDicType(DataDicTypePo po) {
		int rtn = 0;
		try {
			DBConnection.beginTransaction();
			rtn = dao.addDataDicType(po);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			e.printStackTrace();
		}
		return rtn;
	}
	public int updateDataDicType(DataDicTypePo po) {
		int rtn = 0;
		try {
			DBConnection.beginTransaction();
			rtn = dao.updateDataDicType(po);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			e.printStackTrace();
		}
		return rtn;
	}
	public int deleteDataDicType(DataDicTypePo[] pos) {
		int rtn = 0;
		try {
			DBConnection.beginTransaction();
			for (DataDicTypePo po : pos) {
				dao.deleteDataDicType(po);
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