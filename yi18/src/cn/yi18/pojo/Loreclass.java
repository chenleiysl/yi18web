package cn.yi18.pojo;

/**
 * 健康知识分类
 * @author 陈磊
 *
 */
public class Loreclass extends POJO 
{
	protected String name;
	protected String description;
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
	
	@Override
	protected boolean isObjectCachedByID() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
