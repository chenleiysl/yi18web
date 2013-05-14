package cn.yi18.pojo;

/**
 * 生产厂家
 * @author 陈磊
 *
 */
public class Factory extends POJO 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;//生产厂家的的名称
	protected String description;//厂家的基本描述，
	protected String  address;//生产厂家的地址
	protected String phone;//厂家的联系电话，多个用 ， 号隔开
	protected String url;//生产厂家的网站主页
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	protected boolean isObjectCachedByID() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
