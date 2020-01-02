package mybean.data;

import java.util.LinkedList;

public class Login {
	String logsno = "", backNews = "未登录";
	LinkedList<String> car;
	
	public Login() {
		car = new LinkedList<String>();
	}
	
	public String getLogsno() {
		return logsno;
	}
	public void setLogsno(String logsno) {
		this.logsno = logsno;
	}
	public String getBackNews() {
		return backNews;
	}
	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	public LinkedList<String> getCar() {
		return car;
	}
	
}
