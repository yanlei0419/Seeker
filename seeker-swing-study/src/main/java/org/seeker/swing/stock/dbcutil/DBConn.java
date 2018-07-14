package org.seeker.swing.stock.dbcutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBConn {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;


	public void getConnection(){
		try {
			Class.forName(Config.DRIVER_NAME);	
			String url = Config.DB_URL + Config.IP_ADD + ":" + Config.DB_PORT
					+ ":" + Config.DB_NAME;
			this.conn =DriverManager.getConnection(url, Config.ACCOUNT,	Config.PASSWORD);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
	}
	public ResultSet ExeQuery(final String strSql,final Object...params){
		this.getConnection();
		try {
			System.out.println("SQL:> " + strSql); 
			this.ps = this.conn.prepareStatement(strSql);
			for (int i = 0; i < params.length; i++) {
				System.out.println(params[i]);
				System.out.println(i+1);
				
				this.ps.setObject(i + 1, params[i]);
				
			}
			this.rs = this.ps.executeQuery();
			return this.rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//this.closeConnection();
		}
		return null;
	}
	public int ExeUpdate( final String strSql,final Object...params){
		this.getConnection(); 
		System.out.println(params.length);
	try {
		System.out.println("SQL:> " + strSql); 
		this.ps = this.conn.prepareStatement(strSql);
		for (int i = 0; i < params.length; i++) {
			System.out.println(params[i]);
			this.ps.setObject(i + 1, params[i]);
		}
		int affectedRows = this.ps.executeUpdate();
		return affectedRows;
	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection();
		}
		return -1;
	}
	public void closeConnection(){
		if(this.rs != null){
			try {
				this.rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(this.ps != null){
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(this.conn != null){
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
}
