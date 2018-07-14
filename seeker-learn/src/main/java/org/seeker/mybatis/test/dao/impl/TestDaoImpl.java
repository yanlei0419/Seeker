package org.vegetto.mybatis.test.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.seeker.common.base.mybatis.db.impl.BaseDaoImpl;
import org.vegetto.mybatis.test.dao.TestDao;
import org.vegetto.mybatis.test.entity.TestEntity;

@Repository("testDao")public class TestDaoImpl extends BaseDaoImpl<TestEntity> implements TestDao {

	public List<TestEntity> findAll() {
		return super.getSessionTemplate().selectList(this.getStatement("listAll"));
	}

}
