package com.wd.api.cases;

import java.util.Map;

public class DBQueryResult {
private String no;
//�ű�ִ�в鵽�����ݣ����浽map�У�key��������ֶ�����value�����Ӧ�ֶβ鵽������
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
