package cn.yi18.app.entity;

import java.io.Serializable;

public class Drug  implements Serializable{

	private Long id;
	private String name ;//名称
	private String alias ;//别名
	private String term;//药品词条，基本信息
	private String image ;// 图片
	private String prescription;//药品的处方类型，1：处方药，0：非处方的药
	private String ingredient;//药品的组成成分，0：中药，1：中成药：2西药
	private float price;//产考价格
	private String factory;//生产厂家
	private String drugclass;//药品分类
	private int count;//访问次数
	private String message;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	
	public String getDrugclass() {
		return drugclass;
	}
	public void setDrugclass(String drugclass) {
		this.drugclass = drugclass;
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
