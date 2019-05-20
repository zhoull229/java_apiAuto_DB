package com.wd.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**���ݿ��ѯ
 * @author Lenovo
 *
 */
public class JDBCUtil {

	public static Properties properties=new Properties();
	/**
	 * ����properties�ļ�������
	 */
	static{
		System.out.println("����properties�����ļ�");
		InputStream inStream;
		try {
			inStream=new FileInputStream(new File("src/test/resources/jdbc.properties"));
			properties.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**ִ��sql��䲢���ؽ��
	 * @param sql
	 * @return
	 */
	public static Map<String, Object> query(String sql) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
//			//��ȡ���ݿ�����
			Connection connection  = getConnection();
			// ��ȡprepareStatement���󣨴����͵Ķ������ṩ���ݿ����������
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// ���ò�ѯ������ִ�в�ѯ�����ؽ����ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();
			// ��ȡ��ѯ��ص���Ϣ
			ResultSetMetaData metaData = resultSet.getMetaData();
			// ��ȡ��ѯ����ֶεĸ���
			int columCount = metaData.getColumnCount();

			// �ӽ����ȡ����
			while (resultSet.next()) {
				for (int i = 1; i <= columCount; i++) {
					// �����е���Ż�ȡ����
					String columnLable = metaData.getColumnLabel(i);
					//�����������ֵ��ת�����ַ�������
					String columnValue = resultSet.getObject(columnLable).toString();
					resultMap.put(columnLable, columnValue);

				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultMap;

	}

	
	
	/**�������ݿ�
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		String url=properties.getProperty("jdbc.url");
		String username=properties.getProperty("jdbc.username");
		String password=properties.getProperty("jdbc.password");
		Connection connection=DriverManager.getConnection(url, username, password);
		return connection;
		
	}
}
