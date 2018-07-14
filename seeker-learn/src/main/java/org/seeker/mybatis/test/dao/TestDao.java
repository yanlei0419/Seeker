package org.seeker.mybatis.test.dao;


import org.seeker.common.base.mybatis.db.BaseDao;
import org.seeker.mybatis.test.entity.TestEntity;

import java.util.List;

public interface TestDao extends BaseDao<TestEntity> {

	List<TestEntity> findAll();

}
