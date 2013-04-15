package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;

import cn.yi18.enums.DirectoryEnum;

/**
 * 药品内容的目录
 * @author 陈磊
 *
 */
public class Directory extends POJO 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String title;//标题
	protected int issearch = DirectoryEnum.Search_Status.IsSearch.getValue();//是否允许搜索 ，默认允许
	protected String description;//描述
	protected Timestamp time = new Timestamp(new Date().getTime());//创建时间
	protected int sequence;//目录的线索顺序
	protected int type;//类型
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIssearch() {
		return issearch;
	}
	public void setIssearch(int issearch) {
		this.issearch = issearch;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
