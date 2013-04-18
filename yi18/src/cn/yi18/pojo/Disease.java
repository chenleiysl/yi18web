package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Disease extends POJO
{
	protected String name;//疾病名称
	protected String description;//疾病描述 
	protected Long diseaseclass;//疾病分类
	protected int infectious;//传 染 性 0：不传染，1传染
	protected int allow;//是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过
	protected Timestamp time = new Timestamp(new Date().getTime());//时间
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAllow() {
		return allow;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}
	public Long getDiseaseclass() {
		return diseaseclass;
	}
	public void setDiseaseclass(Long diseaseclass) {
		this.diseaseclass = diseaseclass;
	}
	public int getInfectious() {
		return infectious;
	}
	public void setInfectious(int infectious) {
		this.infectious = infectious;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	

	public String subDescription(int size)
	{
		String r=StringUtils.substring(description, 0, size);
		if (description.length()>size) 
		{
			r=r+"…";
		}
		return r;
	}
}
