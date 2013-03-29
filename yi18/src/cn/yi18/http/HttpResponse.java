package cn.yi18.http;

import javax.servlet.http.HttpServletResponse;

public interface HttpResponse extends HttpServletResponse {
	
	
	public void addAutoLoginCookie(String hkey) ;
	public void removeAutoLoginCookie() ;
}
