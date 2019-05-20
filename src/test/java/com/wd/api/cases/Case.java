package com.wd.api.cases;

public class Case {
private String  caseId;
private String  desc;
private String  apiId;
private String  params;
private String actualResponseData;
private String preSql;
private String preResult;
private String afterSql;
private String afterResult;
public String getActualResponseData() {
	return actualResponseData;
}
public void setActualResponseData(String actualResponseData) {
	this.actualResponseData = actualResponseData;
}
public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public String getApiId() {
	return apiId;
}
public void setApiId(String apiId) {
	this.apiId = apiId;
}
public String getParams() {
	return params;
}
public void setParams(String params) {
	this.params = params;
}

public String getPreSql() {
	return preSql;
}
public void setPreSql(String preSql) {
	this.preSql = preSql;
}
public String getPreResult() {
	return preResult;
}
public void setPreResult(String preResult) {
	this.preResult = preResult;
}
public String getAfterSql() {
	return afterSql;
}
public void setAfterSql(String afterSql) {
	this.afterSql = afterSql;
}
public String getAfterResult() {
	return afterResult;
}
public void setAfterResult(String afterResult) {
	this.afterResult = afterResult;
}

public Case() {
	super();
	// TODO Auto-generated constructor stub
}

public Case(String caseId, String desc, String apiId, String params, String actualResponseData, String preSql,
		String preResult, String afterSql, String afterResult) {
	super();
	this.caseId = caseId;
	this.desc = desc;
	this.apiId = apiId;
	this.params = params;
	this.actualResponseData = actualResponseData;
	this.preSql = preSql;
	this.preResult = preResult;
	this.afterSql = afterSql;
	this.afterResult = afterResult;
}
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return caseId+desc+apiId+params+actualResponseData+preSql+preResult+afterSql+afterResult;
	}

}
