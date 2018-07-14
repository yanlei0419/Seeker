package org.seeker.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取数据库连接
 * 
 */
public class DBUtil {
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(DBUtil.class);

	/**
	 * MyBatis的SqlSessionFactory
	 */
	private static volatile SqlSessionFactory sqlSessionFactory;

	/**
	 * 获取SqlSessionFactory，如果已经初始化直接返回，否则新建一个
	 * 
	 * @return SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactorty() {

		logger.debug("获取SqlSessionFactory");
		if (sqlSessionFactory != null) {
			return sqlSessionFactory;
		}
		logger.debug("开始初始化SqlSessionFactory");

		// 配置文件路径
		String resource = "org/pbccrc/bmcas/bufferlayer/mybatis/mybatis-config.xml";

		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("载入MyBatis配置文件时出现异常", e);
		}

		// 初始化SqlSessionFactory的过程中同时载入可配置的参数，参数列表中CacheQueryParameters.properties读取,配置文件在/conf/conf.properties
//		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,Parameters.properties);
		//TODO 
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		logger.debug("初始化SqlSessionFactory成功");
		return sqlSessionFactory;
	}

	/**
	 * 获取一个打开的Session
	 * 
	 * @return 打开的session
	 */
	public static SqlSession openSession() {
		return getSqlSessionFactorty().openSession(true);
	}
}
