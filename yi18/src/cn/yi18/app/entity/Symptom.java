package cn.yi18.app.entity;

import java.io.Serializable;

public class Symptom implements Serializable{

	private long id;
	private String name ;//名称

	private String symptomsclass;//病状分类
	private int count;//访问次数
	private String message;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSymptomsclass() {
		return symptomsclass;
	}
	public void setSymptomsclass(String symptomsclass) {
		this.symptomsclass = symptomsclass;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
