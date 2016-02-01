package com.recomdata.i2b2.dao;

/**
 * Copyright(c)  2011-2012 Recombinant Data Corp., All rights Reserved
 * JDBC DB Connection and utilities for I2B2 database.
 * @author Alex Wu
 * @date August 26, 2011
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.recomdata.config.Config;


public class I2B2DBUtils
{
	private static Connection conn = null;
	final static Logger logger = LogManager.getLogger(I2B2DBUtils.class);
	
	//saj
	private static String ws = null;
	
	private I2B2DBUtils(){}
	
	public static void init(Config config) throws ClassNotFoundException, SQLException {
		String jdbcDriver = config.getProperty("chb.i2b2.jdbc.driver");
		String jdbcUrl = config.getProperty("chb.i2b2.jdbc.url");
		String dbUser = config.getProperty("chb.i2b2.jdbc.dbuser");
		String dbPass = config.getProperty("chb.i2b2.jdbc.dbpasswd");
		ws = config.getProperty("add.ws");
		
		System.out.println("jdbcDriver " + jdbcDriver);
		System.out.println("jdbcUrl " + jdbcUrl);
		System.out.println("dbUser " + dbUser);
		System.out.println("dbPass " + dbPass);
		
		logger.info("1 jdbcDriver " + jdbcDriver + " jdbcUrl " + jdbcUrl + " dbUser " + dbUser + "dbPass " + dbPass);
		
		init(jdbcUrl, dbUser, dbPass, jdbcDriver, "");
	}
	
	public static void init(String jdbcUrl, String dbUser, String dbPass, String jdbcDriver, String webService)
	throws ClassNotFoundException, SQLException {
		String url = jdbcUrl;
		ws = webService;
		
		Properties props = new Properties();
		props.setProperty("user", dbUser);
		props.setProperty("password", dbPass);
		props.setProperty("ssl", "false");

		System.out.println("2 jdbcDriver " + jdbcDriver + " jdbcUrl " + jdbcUrl + " dbUser " + dbUser + "dbPass " + dbPass);
		
		logger.info("3 jdbcDriver " + jdbcDriver + " jdbcUrl " + jdbcUrl + " dbUser " + dbUser + "dbPass " + dbPass + " ws " + ws);
		
		Class.forName(jdbcDriver);
		//conn = DriverManager.getConnection(url, props);
		conn = DriverManager.getConnection(url, dbUser, dbPass);
		
		System.out.println("conn " + conn);
		logger.info("conn init " + conn);
	}
	
	public static void shutdown() {
		try {
			logger.info("conn shutdown " + conn);
			System.out.println("conn shutdown " + conn);
			conn.close();
		} catch (SQLException e) {
			logger.info("Error closing connection");
			System.out.println("Error closing connection");
			e.printStackTrace();
		}
	}

        //saj
	public static String getWS() {
	    return ws;
	}
	
	/** Get DB Connection using JDBC Driver and URL
	*/
	public static Connection getI2B2DBConnection() {
		logger.info("getI2B2DBConnection 1 ");
		System.out.println("getI2B2DBConnection 1 ");
		if (conn == null) {
			logger.info("getI2B2DBConnection 2 ");
			System.out.println("getI2B2DBConnection 2 ");
			throw new RuntimeException("Connection not initialized. Call init() first.");
		}
		
		return conn;
	}

	/**
	* Convert util.Date into sql Date
	*/

	public static java.sql.Date getSQLDateFromUtilDate(java.util.Date dt)
	{
		java.sql.Date dte = null;
		
		try {

			dte = new java.sql.Date(dt.getTime());

		}catch(Exception e){
			e.printStackTrace();
		}

		return dte;
	}

	/**
	* Convert Date String into sql Date
	*/

	public static java.sql.Date getSQLDateFromStr(String s)
	{
	   	java.sql.Date dte=null;
	   	try{
		   	String str = s;
		   	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		   	java.util.Date dt = formatter.parse(str);
		   	dte=new java.sql.Date(dt.getTime());
	   	}catch(Exception e){
	   		e.printStackTrace();
	  	}

	  	return dte;
	}
}
