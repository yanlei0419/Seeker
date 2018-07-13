package junit.mybatis;

import java.util.List;

import org.junit.Test;
import org.vegetto.common.junit.BaseJunit;
import org.vegetto.mybatis.test.biz.TestBiz;
import org.vegetto.mybatis.test.entity.TestEntity;

public class TestDaoTest extends BaseJunit {
	@Test public void create(){
		TestBiz biz=(TestBiz) factory.getBean("testBiz");
		TestEntity e=new TestEntity();
		e.setId(System.currentTimeMillis()+"");
		e.setName("yanlei");
		e.setSex("1");
		biz.create(e);
	}
	@Test public void getAll(){
		TestBiz biz=(TestBiz) factory.getBean("testBiz");
		List<TestEntity> list=biz.getAll();
		for (TestEntity t : list) {
			System.out.println(t.getId()+t.getName()+t.getSex());
		}
	}

}
