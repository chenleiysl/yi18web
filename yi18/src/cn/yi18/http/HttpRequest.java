package cn.yi18.http;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public interface HttpRequest extends HttpServletRequest {

	public String basePath();
	public String getAction();
	public String getModule();
	public String[] getParams();
	public boolean isSubmit();
	//public ServletContext getServletContext();
}
