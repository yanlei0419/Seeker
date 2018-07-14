package org.seeker.common.base.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.seeker.common.base.license.LicenseUtils;
@Deprecated
public class DBManager {
	protected static Logger logger = Logger.getLogger(DBManager.class.getName());
	private static DataSource dataSource;
	private static String dataName;

	public DataSource getDataSource() {
		return dataSource;
	}
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		if(this.dataSource==null){
			logger.error("获取数据库DataSource出问题!!!!");
			System.exit(0);
		}
		//获取数据库名称		
		setDataName();
		LicenseUtils.Verify(logger);
	}
	
	public static void setDataName(){
		try {
			DatabaseMetaData dbMeta = dataSource.getConnection().getMetaData();
			dataName=dbMeta.getDriverName(); //rs.getInt(1)
		} catch (SQLException e) {
			logger.error("获取数据库类型有问题!!!!",e);
			System.exit(0);
			e.printStackTrace();
		}
	}
	public static String getDataName() {
		return dataName;
	}
	//大写
	public static String toDataNameUpper() {
		return dataName.toUpperCase();
	}
	//小写
	public static String toDataNameLower() {
		return dataName.toLowerCase();
	}

}