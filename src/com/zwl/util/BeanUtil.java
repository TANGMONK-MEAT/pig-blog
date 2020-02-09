package com.zwl.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {
	/**
	 * 通用的增删改查方法
	 * @param sql 执行增删改的sql
	 * @param arr 需要插入的数据,即设置的通配符的值
	 * @return true 代表sql执行成功，否则失败
	 * @throws SQLException 
	 */
	public static boolean addUpdateDelete(String sql,Object[] arr) throws SQLException {
		Connection connection = C3P0Util.getConnection();;
		PreparedStatement preparedStatement =  connection.prepareStatement(sql);// 获取预处理对象（即，sql的执行者对象）
		// 设置通配符的值
		if (arr != null && arr.length != 0) {
			// 循环设置
			for (int i = 0; i < arr.length; i++) {
				preparedStatement.setObject(i + 1, arr[i]);
			}
		}
		int counts = preparedStatement.executeUpdate();// 执行sql语句
		C3P0Util.closeAll(null, preparedStatement, connection);// 释放资源
		// 判断是否执行成功
		if (counts > 0) {
			return true;// 插入数据成功
		} else {
			return false;// sql没有被执行成功
		}
	}
	
	/**
	 * 通用的查询方法,封装数据到实体类
	 * @param sql	不带通配符的sql
	 * @param objClass	实体类的class
	 * @return
	 * @throws SQLException
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static List<Object> query(String sql,Class<?> objClass) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Connection connection = C3P0Util.getConnection();
		//执行查询
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.first() == false) {
			//关闭资源
			C3P0Util.closeAll(resultSet, preparedStatement, connection);
			return null;
		}else {
			//指定的行号为零，则光标移动到结果集的第一行之前 
			resultSet.absolute(0);
			//存放查询到的实体类的集合
			List<Object> queryResult = new ArrayList<>();
			//获取实体对象的set方法和属性
			Map<String,Method> objMap = new HashMap<String,Method>();
			//获取实体类的私有属性数组
			Field[] fields = objClass.getDeclaredFields();
			//获取实体类的方法数组
			Method[] methods = objClass.getDeclaredMethods();
			//赋予class对象对私有属性的访问权限
			Field.setAccessible(fields, true);
			//循环遍历所有属性和方法，将属性和setXXX方法进行配对，即添加到map集合中，形成映射关系
			for(Method setMethod : methods) {
				for(Field field : fields) {
					//判断方法是否为setXXX方法，XXX的小写是否和属性名配对
					if(setMethod.getName().startsWith("set") && setMethod.getName().contains(field.getName().toLowerCase())) {
						objMap.put(field.getName(), setMethod);//添加到map集合中
					}
				}
			}
			//循环获取结果集的记录，利用反射执行set方法
			while(resultSet.next()) {
				//获取实体类的对象
				Object obj = objClass.getDeclaredConstructor().newInstance();
				for(String field : objMap.keySet()) {
					//获取属性值对应的方法
					Method method = objMap.get(field);
					//获取方法的参数的类型，此处只有一个参数
					String parameterType = method.getParameterTypes()[0].getSimpleName();
					//判断参数的类型
					if(parameterType.toLowerCase().equals("string")) {
						method.invoke(obj,resultSet.getString(field));
					}else if(parameterType.toLowerCase().equals("int")) {
						method.invoke(obj,resultSet.getInt(field));
					}else if(parameterType.toLowerCase().equals("long")){
						method.invoke(obj,resultSet.getLong(field));
					}else if(parameterType.toLowerCase().equals("date")) {
						method.invoke(obj,resultSet.getDate(field));
					}else if(parameterType.toLowerCase().equals("boolean")) {
						method.invoke(obj,resultSet.getBoolean(field));
					}
					//将实体类添加到list集合
					queryResult.add(obj);
				}
			}
			//关闭资源
			C3P0Util.closeAll(resultSet, preparedStatement, connection);
			return queryResult;
		}
	}
}
