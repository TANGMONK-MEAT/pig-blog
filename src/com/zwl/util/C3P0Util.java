package com.zwl.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * C3P0连接池
 * 		该连接池实现了javax.sql.DataSource接口
 *
 * 1，javax.sql.DataSource接口的实现类是：
 * 		ComboPooledDataSource
 *
 * 注意：
 * 		1），C3P0的配置文件(.xml)不可以改名字，否则加载时，找不到配置文件
 * 		2），C3P0的配置文件不需要调用连接池对象主动加载，
 * 			只要配置文件在src目录下，实例化连接池对象时，该对象会自动加载（.xml）配置文件。
 */
public class C3P0Util {
//	//调用无参构造方法
//	//创建连接池对象,该对象会自动在src目录下找到配置文件（.xml）,加载和数据库连接的四大要素
//	private static ComboPooledDataSource pool= new ComboPooledDataSource();

	//调用有参构造方法
	//创建连接池对象,该对象会自动在src目录下找到配置文件（My_C3P0_Property.xml）,加载和数据库连接的四大要素
	private static ComboPooledDataSource pool= new ComboPooledDataSource();


	/**
	 * 从C3P0连接池中，返回和MySQL数据库的连接对象
	 * databaseName 	MySQL的数据库名称
	 * @accounts     	MySQL的账号
	 * password     	MySQL的密码
	 * @return 和MySQL的指定的数据库的连接对象
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		// 通过连接池对象，获取和MySQL的指定的数据库连接对象
		conn = pool.getConnection();
		System.out.println("和MySQL数据库连接成功");
		return conn;
	}
	/**
	 * 关闭资源
	 *
	 * @param res  结果集对象
	 * @param stmt 执行SQL语句的对象
	 * @param conn 和数据库连接的对象
	 */
	public static void closeAll(ResultSet res, Statement stmt, Connection conn) {
		try {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("资源关闭失败");
		}
	}
	/**
	 * 返回连接池对象
	 */
	public static DataSource getDataSource() {
		return pool;
	}

}


