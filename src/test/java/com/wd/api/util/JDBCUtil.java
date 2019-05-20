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

/**数据库查询
 * @author Lenovo
 *
 */
public class JDBCUtil {

	public static Properties properties=new Properties();
	/**
	 * 解析properties文件的数据
	 */
	static{
		System.out.println("解析properties配置文件");
		InputStream inStream;
		try {
			inStream=new FileInputStream(new File("src/test/resources/jdbc.properties"));
			properties.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**执行sql语句并返回结果
	 * @param sql
	 * @return
	 */
	public static Map<String, Object> query(String sql) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
//			//获取数据库连接
			Connection connection  = getConnection();
			// 获取prepareStatement对象（此类型的对象有提供数据库操作方法）
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// 调用查询方法，执行查询，返回结果集ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();
			// 获取查询相关的信息
			ResultSetMetaData metaData = resultSet.getMetaData();
			// 获取查询结果字段的个数
			int columCount = metaData.getColumnCount();

			// 从结果集取数据
			while (resultSet.next()) {
				for (int i = 1; i <= columCount; i++) {
					// 根据列的序号获取列名
					String columnLable = metaData.getColumnLabel(i);
					//根据列名获得值并转换成字符串类型
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

	
	
	/**连接数据库
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
