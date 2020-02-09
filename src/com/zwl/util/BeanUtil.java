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
	 * ͨ�õ���ɾ�Ĳ鷽��
	 * @param sql ִ����ɾ�ĵ�sql
	 * @param arr ��Ҫ���������,�����õ�ͨ�����ֵ
	 * @return true ����sqlִ�гɹ�������ʧ��
	 * @throws SQLException 
	 */
	public static boolean addUpdateDelete(String sql,Object[] arr) throws SQLException {
		Connection connection = C3P0Util.getConnection();;
		PreparedStatement preparedStatement =  connection.prepareStatement(sql);// ��ȡԤ������󣨼���sql��ִ���߶���
		// ����ͨ�����ֵ
		if (arr != null && arr.length != 0) {
			// ѭ������
			for (int i = 0; i < arr.length; i++) {
				preparedStatement.setObject(i + 1, arr[i]);
			}
		}
		int counts = preparedStatement.executeUpdate();// ִ��sql���
		C3P0Util.closeAll(null, preparedStatement, connection);// �ͷ���Դ
		// �ж��Ƿ�ִ�гɹ�
		if (counts > 0) {
			return true;// �������ݳɹ�
		} else {
			return false;// sqlû�б�ִ�гɹ�
		}
	}
	
	/**
	 * ͨ�õĲ�ѯ����,��װ���ݵ�ʵ����
	 * @param sql	����ͨ�����sql
	 * @param objClass	ʵ�����class
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
		//ִ�в�ѯ
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.first() == false) {
			//�ر���Դ
			C3P0Util.closeAll(resultSet, preparedStatement, connection);
			return null;
		}else {
			//ָ�����к�Ϊ�㣬�����ƶ���������ĵ�һ��֮ǰ 
			resultSet.absolute(0);
			//��Ų�ѯ����ʵ����ļ���
			List<Object> queryResult = new ArrayList<>();
			//��ȡʵ������set����������
			Map<String,Method> objMap = new HashMap<String,Method>();
			//��ȡʵ�����˽����������
			Field[] fields = objClass.getDeclaredFields();
			//��ȡʵ����ķ�������
			Method[] methods = objClass.getDeclaredMethods();
			//����class�����˽�����Եķ���Ȩ��
			Field.setAccessible(fields, true);
			//ѭ�������������Ժͷ����������Ժ�setXXX����������ԣ�����ӵ�map�����У��γ�ӳ���ϵ
			for(Method setMethod : methods) {
				for(Field field : fields) {
					//�жϷ����Ƿ�ΪsetXXX������XXX��Сд�Ƿ�����������
					if(setMethod.getName().startsWith("set") && setMethod.getName().contains(field.getName().toLowerCase())) {
						objMap.put(field.getName(), setMethod);//��ӵ�map������
					}
				}
			}
			//ѭ����ȡ������ļ�¼�����÷���ִ��set����
			while(resultSet.next()) {
				//��ȡʵ����Ķ���
				Object obj = objClass.getDeclaredConstructor().newInstance();
				for(String field : objMap.keySet()) {
					//��ȡ����ֵ��Ӧ�ķ���
					Method method = objMap.get(field);
					//��ȡ�����Ĳ��������ͣ��˴�ֻ��һ������
					String parameterType = method.getParameterTypes()[0].getSimpleName();
					//�жϲ���������
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
					//��ʵ������ӵ�list����
					queryResult.add(obj);
				}
			}
			//�ر���Դ
			C3P0Util.closeAll(resultSet, preparedStatement, connection);
			return queryResult;
		}
	}
}
