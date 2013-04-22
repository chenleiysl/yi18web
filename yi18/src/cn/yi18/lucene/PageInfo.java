package cn.yi18.lucene;

import java.sql.Timestamp;

/**
 * 索引的对象
 * @author 陈磊
 *
 */
public class PageInfo
{
	private long id;//索引的编号
	private String url;//页面的URL
	private String title;//索引的标题
	private String content;// 内容
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
