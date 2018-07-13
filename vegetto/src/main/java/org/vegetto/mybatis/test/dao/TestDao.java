package org.vegetto.mybatis.test.dao;

import java.util.List;

import org.vegetto.common.base.mybatis.db.BaseDao;
import org.vegetto.mybatis.test.entity.TestEntity;

public interface TestDao extends BaseDao<TestEntity> {

	List<TestEntity> findAll();

}
