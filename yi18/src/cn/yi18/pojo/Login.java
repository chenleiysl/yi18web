package cn.yi18.pojo;

import java.sql.Timestamp;

/**
 * 登录信息记录
 * @author 陈磊
 *
 */
public class Login extends POJO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long user;//用户id
	private String session;//session id
	private String hkey;//验证登录的key
	private Timestamp logintime;//登录时间
	private int isonline;//是否在线
	private String ip;//登录ip
	private int isauto;//是否自动登录
	public long getUser() {
		return user;
	}
	public void setUser(long user) {
		this.user = user;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getHkey() {
		return hkey;
	}
	public void setHkey(String hkey) {
		this.hkey = hkey;
	}
	public Timestamp getLogintime() {
		return logintime;
	}
	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}
	public int getIsonline() {
		return isonline;
	}
	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getIsauto() {
		return isauto;
	}
	public void setIsauto(int isauto) {
		this.isauto = isauto;
	}
	
	
	
	
	

}
