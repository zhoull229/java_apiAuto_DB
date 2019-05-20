package com.wd.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wd.api.cases.DBChecker;
import com.wd.api.cases.DBQueryResult;

/**执行查询并返回结果
 * @author Lenovo
 *
 */
public class DBUtil {

	public static String doQuery(String sql) {
		//将json字符串数据封装成dbchecker类型的对象集合
		List<DBChecker> dbCheckers=JSONObject.parseArray(sql,DBChecker.class);
		//创建DBQueryResult类型的集合存放查询的结果
		List<DBQueryResult> dbQueryResults=new ArrayList<DBQueryResult>();
		for (DBChecker dbChecker : dbCheckers) {
			String no=dbChecker.getNo();
			String sql1=dbChecker.getSql();
			//调用查询方法得到结果
			Map<String, Object> columbLabelsAndValues=JDBCUtil.query(sql1);
			//将结果放到dbQueryResults集合中返回
			DBQueryResult dbQueryResult=new DBQueryResult();
			dbQueryResult.setNo(no);
			dbQueryResult.setColumenLabelAndValues(columbLabelsAndValues);
			dbQueryResults.add(dbQueryResult);
		
		}
		return JSONObject.toJSONString(dbQueryResults);
	}
}
