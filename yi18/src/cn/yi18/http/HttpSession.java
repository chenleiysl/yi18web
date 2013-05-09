package cn.yi18.http;

import cn.yi18.pojo.User;




public interface HttpSession  {

	public void setAttribute(String name, Object value);

	public 	void removeAttribute(String name);

	public Object getAttribute(String name);
	
	public User getUser(String userid) ;
	public void deleteUser(String userid) ;
	public void setUser(User user);

}
