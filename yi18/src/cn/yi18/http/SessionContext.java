package cn.yi18.http;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;




public class SessionContext  implements cn.yi18.http.HttpSession {

	
	private HttpSession httpSession;
	private HttpRequest httpRequest ;

	public SessionContext(HttpRequest httpRequest)
	{
		this.httpSession = httpRequest.getSession();
		this.httpRequest = httpRequest;
	}

	@Override
	public void setAttribute(String name, Object value)
	{
		httpSession.setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name)
	{
		httpSession.removeAttribute(name);
	}

	@Override
	public Object getAttribute(String name)
	{
		return httpSession.getAttribute(name);
	}

	@Override
	public String getId()
	{
		return httpSession.getId();
	}


	@Override
	public Enumeration<?> getAttributeNames()
	{
		return httpSession.getAttributeNames();
	}

	@Override
	public void invalidate()
	{
		httpSession.invalidate();
	}

	/**
	 * 判断用户是否登录
	 */
	@Override
	public boolean isLogin() 
	{
		boolean ret = false;
//		ret= this.getAttribute("user")!=null?true:false;	
//		 if(!ret)
//		 {
//			 String isauto=(String) this.getAttribute("auto");
//			 if(isauto==null)
//			 {		
//				 if(_getCookie() > 0) ret= true;
//			 }else if(isauto.equals("1"))
//			 {
//				 Login login = (Login) this.getAttribute("login");
//				 if(login!=null) ret= true;
//			}
//		 }
		return ret;
	}

	

	
	/**
	 * 验证cookie登录
	 * 如果cookie实现自动登录，就返回user的id
	 * @return
	 */
	private long _getCookie()
	{
		
		return 0;
		
	}

	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub
		
	}
	

}
