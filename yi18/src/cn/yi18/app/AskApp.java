package cn.yi18.app;

import java.io.Serializable;

public class AskApp  implements Serializable
{
	 private long id;
	 private String callback;//Jsonp返回数据
	 private int limit;//显示大小
	 private int page;//当前页
	 private int start;//开始条数
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	 
	 
	 
}
