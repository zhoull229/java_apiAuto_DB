package com.wd.api.cases;

import java.util.Map;

public class DBQueryResult {
private String no;
//脚本执行查到的数据，保存到map中，key保存的是字段名，value保存对应字段查到的数据
private Map<String, Object> columenLabelAndValues;

public String getNo() {
	return no;
}
public void setNo(String no) {
	this.no = no;
}
public Map<String, Object> getColumenLabelAndValues() {
	return columenLabelAndValues;
}
public void setColumenLabelAndValues(Map<String, Object> columenLabelAndValues) {
	this.columenLabelAndValues = columenLabelAndValues;
}

}
