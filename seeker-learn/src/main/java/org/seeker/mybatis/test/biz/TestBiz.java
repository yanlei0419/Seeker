package org.seeker.mybatis.test.biz;


import org.seeker.mybatis.test.dao.TestDao;
import org.seeker.mybatis.test.entity.TestEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
