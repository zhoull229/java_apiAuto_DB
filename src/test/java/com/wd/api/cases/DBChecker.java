package com.wd.api.cases;
/**
 * ���ݿ��ѯ���������
 * @author Lenovo
 *
 */

public class DBChecker {
private String no;
private String sql;
public String getNo() {
	return no;
}
public void setNo(String no) {
	this.no = no;
}
public String getSql() {
	return sql;
}
public void setSql(String sql) {
	this.sql = sql;
}
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return no+sql;
	}

}
