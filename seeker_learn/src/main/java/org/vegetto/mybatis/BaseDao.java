package org.vegetto.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

/**
 * 基础DAO类，提供与相关增删改查功能
 *         2014-4-3 上午9:23:34<br/>
 */
public class BaseDao {

	/**
	 * 根据给定条件查询出结果集
	 * @param name
	 *            sql片段名称
	 * @param param
	 *            条件对象
	 * @return 分页后的结果集
	 */
	public List selectList(String name, Object param) {
		SqlSession session = DBUtil.openSession();
		List list = null;
		try {
			if (null == param) {
				list = session.selectList(name);
			} else {
				list = session.selectList(name,param);
			}
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * 查询出结果集
	 * @param name
	 *            sql片段名称
	 * @return 结果集
	 */
	public List selectList(String name) {
		return selectList(name, null);
	}

	/**
	 * 根据给定条件查出单个结果
	 * @param name
	 *            sql片段名称
	 * @param param
	 *            条件
	 * @return 单个查询结果
	 */
	public Object selectOne(String name, Object param) {
		SqlSession session = DBUtil.openSession();
		Object obj = null;
		try {
			if (null == param) {
				obj = session.selectOne(name);
			} else {
				obj = session.selectOne(name, param);
			}
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * 根据给定条件查出单个结果
	 * @param name
	 *            sql片段名称
	 * @param param
	 *            条件
	 * @return 单个查询结果
	 */
	public Object selectOne(String name) {
		return selectOne(name, null);
	}

	/**
	 * 插入
	 * @param name
	 *            sql片段名称
	 * @param values
	 *            值，如果为空则不附加值
	 * @return 受影响的记录数
	 */
	public int insert(String name, Object values) {
		SqlSession session = DBUtil.openSession();
		int resCount = 0;
		try {
			if (null == values) {
				resCount = session.insert(name);
			} else {
				resCount = session.insert(name, values);
			}
		} finally {
			session.close();
		}
		return resCount;
	}

	/**
	 * 插入
	 * @param name
	 *            sql片段名称
	 * @param values
	 *            值，如果为空则不附加值
	 * @return 受影响的记录数
	 */
	public int insert(String name) {
		return insert(name, null);
	}

	/**
	 * 更新
	 * @param name
	 *            sql片段名称
	 * @param values
	 *            值，如果为空则不附加值
	 * @return 受影响的记录数
	 */
	public int update(String name, Object values) {

		SqlSession session = DBUtil.openSession();
		int resCount = 0;
		try {
			if (null == values) {
				resCount = session.update(name);
			} else {
				resCount = session.update(name, values);
			}
		} finally {
			session.close();
		}
		return resCount;
	}

	/**
	 * 更新
	 * @param name
	 *            sql片段名称
	 * @param values
	 *            值，如果为空则不附加值
	 * @return 受影响的记录数
	 */
	public int update(String name) {

		return update(name, null);
	}

	/**
	 * 删除
	 * @param name
	 *            sql片段名称
	 * @param values
	 *            值，如果为空则不附加值
	 * @return 受影响的记录数
	 */
	public int delete(String name, Object values) {
		SqlSession session = DBUtil.openSession();
		int resCount = 0;
		try {
			if (null == values) {
				resCount = session.delete(name);
			} else {
				resCount = session.delete(name, values);
			}
		} finally {
			session.close();
		}
		return resCount;
	}

	/**
	 * 删除
	 * @param name
	 *            sql片段名称
	 * @param values
	 *            值，如果为空则不附加值
	 * @return 受影响的记录数
	 */
	public int delete(String name) {
		return delete(name, null);
	}

	public void execute(String sql) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);

		this.selectList("executeSql", map);
	}
}
