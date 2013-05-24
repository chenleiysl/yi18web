package cn.yi18.app.entity;

import java.io.Serializable;

public class Disease implements Serializable{

	private Long id;
	private String name;//疾病名称
	private String message;//疾病描述 
	private String diseaseclass;//疾病分类
	private String infectious;//传 染 性 0：不传染，1传染
	private int count;//访问次数
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDiseaseclass() {
		return diseaseclass;
	}
	public void setDiseaseclass(String diseaseclass) {
		this.diseaseclass = diseaseclass;
	}
	public String getInfectious() {
		return infectious;
	}
	public void setInfectious(String infectious) {
		this.infectious = infectious;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
