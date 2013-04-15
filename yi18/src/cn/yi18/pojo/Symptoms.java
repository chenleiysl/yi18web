package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 药品信息
 * @author 陈磊
 *
 */
public class Symptoms extends POJO 
{
	protected String name ;//名称
	protected String description;//病状词条，基本信息
	protected int allow;//是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过
	protected Long symptomsclass;//病状分类
	protected int count;//访问次数
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



	public Long getSymptomsclass() {
		return symptomsclass;
	}



	public void setSymptomsclass(Long symptomsclass) {
		this.symptomsclass = symptomsclass;
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
