package org.vegetto.common.base.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;


/**
 * 获取
 * @author yanlei
 *
 */
@Deprecated
public class DBConnection {
	protected static Logger logger = Logger.getLogger(DBConnection.class.getName());
	
	private static final ThreadLocal<Connection> local = new ThreadLocal<Connection>();


	public static Connection getConnection() throws SQLException {
		Connection conn = (Connection) local.get();
		try {
			if ((conn == null) || (conn.isClosed())) {
				conn =DBConnectionHandler.getConnection();
				local.set(conn);
			}
		} catch (SQLException e) {
			System.exit(0);
			logger.error("获取数据库连接出问题!!!!",e);
			e.printStackTrace();
		}
		return conn;
	}

	protected void closeConnection() throws SQLException {
		Connection conn = (Connection) local.get();
		try {
			if ((conn != null) && (!conn.isClosed())) {
				conn.setAutoCommit(true);
				//TODO 关闭数据库连接
				conn.close();
			}
		} catch (SQLException localSQLException) {
			logger.error("关闭数据库连接出问题!!!!",localSQLException);
		} finally {
			local.remove();
		}
	}

	public static void beginTransaction() {
		try {
			Connection conn = getConnection();
			
			if ((conn != null) && (!conn.isClosed()))
				conn.setAutoCommit(false);
			
		} catch (SQLException e) {
			logger.error("开启事务失败!",e);
			e.printStackTrace();
		}
	}

	public static void commitTransaction() {
		try {
			Connection conn = getConnection();
			//TODO 事物处理未完成
			conn.commit();
		} catch (SQLException e) {
			logger.error("提交数据出问题!!!!",e);
			e.printStackTrace();
		} finally {
			local.remove();
		}
	}

	public static void rollbackTransaction() {
		try {
			Connection conn = getConnection();
			//TODO 事务处理未完成
			conn.rollback();
		} catch (SQLException e) {
			logger.error("回滚数据出问题!!!!",e);
			e.printStackTrace();
		} finally {
			local.remove();
		}
	}


}
