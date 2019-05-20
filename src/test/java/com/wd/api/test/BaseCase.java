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
		//调用接口前先查询数据库
		if (preSql!=null && preSql.trim().length()>0) {
			String preResult=DBUtil.doQuery(preSql);
			//回写到excel
			ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "PreResult", preResult));
		}
		String url = RestUtil.getUrlByApiId(apiId);
		String type = RestUtil.getTypeByApiId(apiId);
		Map<String, String> param = (Map<String, String>) JSONObject.parse(parameter);
		String result = HttpUtil.doService(url, type, param);
		System.out.println(result);
//		保存回写的数据对象
		ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "ActualResponseData", result));
		//调用接口后查询数据库
				if (afterSql!=null && afterSql.trim().length()>0) {
					String afterResult=DBUtil.doQuery(afterSql);
					//回写到excel
					ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "AfterResult", afterResult));
				}
	}
//批量回写数据,
	@AfterSuite
	public void batchWriteBackDatas() {
		ExcelUtil.batchWriteBaceDatas("src/test/resources/wd.xls");
	}
}
