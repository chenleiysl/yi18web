package cn.yi18.pojo;

/**
 * 友情链接
 * @author 陈磊
 *
 */
public class Links extends POJO
{
	protected String title;//友情链接的标题
	protected String url;//友情链接的URL链接
	protected int focal;//该友情链接是否拥有焦点 0：没有，1：拥有。这个主要拥有友情链接的突出
	protected int sequence;//排序设置友情链接的排序方式，从0开始
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFocal() {
		return focal;
	}
	public void setFocal(int focal) {
		this.focal = focal;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
}
