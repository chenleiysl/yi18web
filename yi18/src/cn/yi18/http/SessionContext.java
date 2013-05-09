package cn.yi18.http;



import javax.servlet.http.Cookie;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.pojo.User;
import cn.yi18.util.DigestSHA;



/**
 * 这里我们主要用的的ehcache来代替传统的session
 * @author 陈磊
 *
 */

public class SessionContext  implements cn.yi18.http.HttpSession {

	
	
	private final String fullyQualifiedName = "session";
	@Override
	public void setAttribute(String name, Object value)
	{
		
		EhCacheEngine.add(fullyQualifiedName, name, value);
	}

	@Override
	public void removeAttribute(String name)
	{
		EhCacheEngine.remove(fullyQualifiedName, name);
	}

	@Override
	public Object getAttribute(String name)
	{
		return EhCacheEngine.get(fullyQualifiedName, name);
	}

	@Override
	public User getUser(String userid) {
		// TODO Auto-generated method stub
		return (User) getAttribute(userid);
	}

	@Override
	public void setUser(User user) 
	{
		String yybid=DigestSHA.SHA224(user.getAccount()+user.getPassword());
//		Cookie cookie = new Cookie("yybid", user.getAccount()+user.getPassword());
//		cookie.setMaxAge(1800); 
		setAttribute(yybid, user);
		
	}

	@Override
	public void deleteUser(String userid) {
		removeAttribute(userid);
		
	}


	
	

}
