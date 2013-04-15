package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 *  病状分类
 * @author 陈磊
 *
 */
public class Symptomclass extends POJO 
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String title; //标题
  protected int level; //等级
  protected Long _parentId;//上级目录的id 默认是1
  protected Timestamp time = new Timestamp(new Date().getTime()); //时间
  protected String state;
  
  
	public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
  
  
  
}
