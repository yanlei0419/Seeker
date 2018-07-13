package org.vegetto.mybatis.test.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.vegetto.mybatis.test.dao.TestDao;
import org.vegetto.mybatis.test.entity.TestEntity;

@Service("testBiz")public class TestBiz {
	private TestDao dao;
	@Resource(name="testDao")
	public void setDao(TestDao dao) {
		this.dao = dao;
	}
	public long create(TestEntity entity){
		return this.dao.insert(entity);
	}
	public List<TestEntity> getAll(){
		return this.dao.findAll();
	}
	public int del(String id){
		return this.dao.deleteById(Long.parseLong(id));
	}

}
