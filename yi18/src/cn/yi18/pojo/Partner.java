package cn.yi18.pojo;

public class Partner extends POJO 
{
	protected String title;//合作伙伴的标题
	protected String logo;//LOGO标志的地址
	protected String url;//合作伙伴的网站
	protected String description;//合作伙伴的描述
	protected int sequence;//合作伙伴的排列顺序
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	
	
}
