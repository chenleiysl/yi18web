package cn.yi18.http;

import java.util.Enumeration;


public interface HttpSession  {

	public void setAttribute(String name, Object value);

	public 	void removeAttribute(String name);

	public Object getAttribute(String name);

	public String getId();

	public Enumeration<?> getAttributeNames();

	public void invalidate();
	
	public boolean isLogin();//用户是否登录
	
	
	public void deleteUser();
}
