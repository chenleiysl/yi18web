package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 综合资讯
 * @author 陈磊
 *
 */
public class News extends POJO
{
	protected String title;//资讯标题
	protected String message;//资讯内容
	protected int count ;//访问次数
	protected Timestamp time = new Timestamp(new Date().getTime());//添加时间
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
