package cn.yi18.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import cn.yi18.enums.CookieEnum;



public class ResponseContext extends HttpServletResponseWrapper implements HttpResponse {

	public ResponseContext(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAutoLoginCookie(String hkey) 
	{
		int maxAge = 3600 * 24 * 365;
		Cookie cookie = new Cookie(CookieEnum.yi18_id.toString(), hkey);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		this.addCookie(cookie);
	
		
	}
	
	public void addAutoLoginCookie(int time,String hkey) 
	{
		int maxAge = time;
		Cookie cookie = new Cookie(CookieEnum.yi18_id.toString(), hkey);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		this.addCookie(cookie);
	
		
	}

	@Override
	public void removeAutoLoginCookie() {
		
		Cookie cookie = new Cookie(CookieEnum.yi18_id.toString(), null);
		cookie.setMaxAge(0);
		//cookie.setPath("/");
		this.addCookie(cookie);
	}

	


}
