package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;

import cn.yi18.enums.UserEnum;




/**
 *  会员信息
 * @author 陈磊
 *
 */
public class User extends POJO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String account;//帐号
	private String password;//密码
	private String name;   //姓名
	private String home;  //首页
	private String image;//头像图片
	private String email;//邮箱
	private String phone;//联系电话 手机，电话等 
	private String qq;//qq号
	private String url;//个人主页网址
	private int sex;//性别，1：男 ;0：女 
	private String birth;//出生年月 
	private String area;//地址如 四川省-成都市
	private String signature;//个性签名
	private int integral=0;//用户积分 
	private int isemail=UserEnum.Mail_Status.NoShow.getValue();//是否显示邮箱 1：显示 0：不显示 
	private int isphone=UserEnum.Phone_Status.NoShow.getValue();//是否显示联系电话 1：显示 0：不显示 
	private int isqq=UserEnum.QQ_Status.NoShow.getValue();//是否显示qq号，1：显示，0：不显示 
//	private Timestamp logintime;//登录时间
//	private int islogin=UserEnum.Login_Status.NoLogin.getValue();//是否登录，1：登录，0：未登录
	private int isuse=UserEnum.Use_Status.NoUse.getValue();//是否可用，如禁止帐号，1：可用，0：不可用,-1:锁定
	private int role=UserEnum.Role_Status.User.getValue();//用户权限，0：普通用户，1：管理员
	private String hkey;//关键钥匙
	private Timestamp time =new  Timestamp(new Date().getTime());//注册时间
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public int getIsemail() {
		return isemail;
	}
	public void setIsemail(int isemail) {
		this.isemail = isemail;
	}
	public int getIsphone() {
		return isphone;
	}
	public void setIsphone(int isphone) {
		this.isphone = isphone;
	}
	public int getIsqq() {
		return isqq;
	}
	public void setIsqq(int isqq) {
		this.isqq = isqq;
	}
//	public Timestamp getLogintime() {
//		return logintime;
//	}
//	public void setLogintime(Timestamp logintime) {
//		this.logintime = logintime;
//	}
//	public int getIslogin() {
//		return islogin;
//	}
//	public void setIslogin(int islogin) {
//		this.islogin = islogin;
//	}
	public int getIsuse() {
		return isuse;
	}
	public void setIsuse(int isuse) {
		this.isuse = isuse;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getHkey() {
		return hkey;
	}
	public void setHkey(String hkey) {
		this.hkey = hkey;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	

}
