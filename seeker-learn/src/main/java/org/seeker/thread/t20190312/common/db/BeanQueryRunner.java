package org.seeker.thread.t20190312.common.db;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.ApplicationContext;
import org.seeker.common.base.spring3.SpringContextUtil;
public class BeanQueryRunner {
	private static ApplicationContext ctx = SpringContextUtil.getApplicationContext();
//	private static DataSourceTransactionManager txManager;
	private static QueryRunner runner;
	private BeanQueryRunner(){};

	public static QueryRunner getRuner() {
		if (runner == null) {
			runner = new QueryRunner();
		}
		return runner;
	}

//	public static DataSourceTransactionManager getTxManager() {
//		if (txManager == null) {
//			txManager = (DataSourceTransactionManager) ctx.getBean("txManager", DataSourceTransactionManager.class);
//		}
//		return txManager;
//	}
}