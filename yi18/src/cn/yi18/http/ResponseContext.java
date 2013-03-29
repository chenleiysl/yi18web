package cn.yi18.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;



public class ResponseContext extends HttpServletResponseWrapper implements HttpResponse {

	public ResponseContext(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAutoLoginCookie(String hkey) 
	{
		int maxAge = 3600 * 24 * 365;
	
		
	}

	@Override
	public void removeAutoLoginCookie() {
		

	}


}
