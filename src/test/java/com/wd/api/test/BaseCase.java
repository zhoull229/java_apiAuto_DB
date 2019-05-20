package com.wd.api.test;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.wd.api.cases.WriteBackData;
import com.wd.api.util.DBUtil;
import com.wd.api.util.ExcelUtil;
import com.wd.api.util.HttpUtil;
import com.wd.api.util.RestUtil;

public class BaseCase {
	String[] cellNames = { "CaseId","ApiId", "Params" ,"PreSql","AfterSql"};
	@Test(dataProvider = "datas")
	public void test( String caseId,String apiId, String parameter,String preSql,String afterSql) {
		//���ýӿ�ǰ�Ȳ�ѯ���ݿ�
		if (preSql!=null && preSql.trim().length()>0) {
			String preResult=DBUtil.doQuery(preSql);
			//��д��excel
			ExcelUtil.writeBackDatas.add(new WriteBackData("����", caseId, "PreResult", preResult));
		}
		String url = RestUtil.getUrlByApiId(apiId);
		String type = RestUtil.getTypeByApiId(apiId);
		Map<String, String> param = (Map<String, String>) JSONObject.parse(parameter);
		String result = HttpUtil.doService(url, type, param);
		System.out.println(result);
//		�����д�����ݶ���
		ExcelUtil.writeBackDatas.add(new WriteBackData("����", caseId, "ActualResponseData", result));
		//���ýӿں��ѯ���ݿ�
				if (afterSql!=null && afterSql.trim().length()>0) {
					String afterResult=DBUtil.doQuery(afterSql);
					//��д��excel
					ExcelUtil.writeBackDatas.add(new WriteBackData("����", caseId, "AfterResult", afterResult));
				}
	}
//������д����,
	@AfterSuite
	public void batchWriteBackDatas() {
		ExcelUtil.batchWriteBaceDatas("src/test/resources/wd.xls");
	}
}
