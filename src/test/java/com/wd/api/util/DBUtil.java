package com.wd.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wd.api.cases.DBChecker;
import com.wd.api.cases.DBQueryResult;

/**ִ�в�ѯ�����ؽ��
 * @author Lenovo
 *
 */
public class DBUtil {

	public static String doQuery(String sql) {
		//��json�ַ������ݷ�װ��dbchecker���͵Ķ��󼯺�
		List<DBChecker> dbCheckers=JSONObject.parseArray(sql,DBChecker.class);
		//����DBQueryResult���͵ļ��ϴ�Ų�ѯ�Ľ��
		List<DBQueryResult> dbQueryResults=new ArrayList<DBQueryResult>();
		for (DBChecker dbChecker : dbCheckers) {
			String no=dbChecker.getNo();
			String sql1=dbChecker.getSql();
			//���ò�ѯ�����õ����
			Map<String, Object> columbLabelsAndValues=JDBCUtil.query(sql1);
			//������ŵ�dbQueryResults�����з���
			DBQueryResult dbQueryResult=new DBQueryResult();
			dbQueryResult.setNo(no);
			dbQueryResult.setColumenLabelAndValues(columbLabelsAndValues);
			dbQueryResults.add(dbQueryResult);
		
		}
		return JSONObject.toJSONString(dbQueryResults);
	}
}
