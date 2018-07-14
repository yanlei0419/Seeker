package org.vegetto.swing.stock.dbcutil;

import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties prop = new Properties();
	static{
		try {
			prop.load(Config.class.getResourceAsStream("dbc.propreties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static final String DRIVER_NAME = prop.getProperty("DRIVER_NAME");
	public static final String DB_URL = prop.getProperty("DB_URL");
	public static final String IP_ADD = prop.getProperty("IP_ADD");
	public static final String DB_PORT = prop.getProperty("DB_PORT");
	public static final String DB_NAME = prop.getProperty("DB_NAME");
	public static final String ACCOUNT = prop.getProperty("ACCOUNT");
	public static final String PASSWORD = prop.getProperty("PASSWORD");
}
