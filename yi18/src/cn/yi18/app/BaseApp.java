package cn.yi18.app;

import java.io.IOException;

import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yi18.enums.CookieEnum;
import cn.yi18.http.*;

import cn.yi18.pojo.Links;

import cn.yi18.service.LinksService;
import cn.yi18.util.Base64Coder;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 所有的action类的父类
 * @author 陈磊
 *
 */
public  abstract class BaseApp  
{

	private static final Logger log= LoggerFactory.getLogger(BaseApp.class);
	@SuppressWarnings("rawtypes")
	private static Class[] NO_ARGS_CLASS = new Class[0];
	private static Object[] NO_ARGS_OBJECT = new Object[0];
	protected HttpRequest request;
	protected HttpResponse response;
	protected ServletContext context;
	protected HttpSession session;
	
	
	/**
	 * 初始化
	 * @param request
	 * @param response
	 */
	public void init(HttpRequest request, HttpResponse response) {
		this.request = request;
		this.response = response;
		//this.context = request.getServletContext(); 3.0
		this.context = request.getSession().getServletContext();
		this.session = new SessionContext();
		
		
	}
	
	/**
	 * 执行的类
	 * @throws ServletException
	 * @throws IOException
	 */
	protected  void run() throws ServletException, IOException
	{
		String action = request.getAction();
		
			//aop调用 执行 action 方法
			
				try {
					this.getClass().getMethod(action, NO_ARGS_CLASS).invoke(this, NO_ARGS_OBJECT);
				} catch (IllegalAccessException e) {
					log.error("{} 类中 运行 {}方法 错\n" + e,this.getClass(),action);
					run_500();
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					log.error("{} 类中 没有实现 {}方法 \n" + e,this.getClass(),action);
					run_500();//返回500
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					log.error("{} 类中 没有实现 {}方法 \n" + e,this.getClass(),action);
					run_404();//返回404
					e.printStackTrace();
				} catch (SecurityException e) {
					log.error("{} 类中 没有实现 {}方法 \n" + e,this.getClass(),action);
					run_500();//返回500
					e.printStackTrace();
				}
		
			

	};
	
	protected void destroy()
	{
		
	}
	
	/**
	 * 默认执行的方法
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute() throws ServletException, IOException 
	{
		log.error("{} 类中 没有继承 execute() 方法 \n" ,this.getClass());

	}
	
	
	
	/**
	 * text/html 格式的返回
	 * @param html 显示的html
	 */
	protected void printHtml(String html) 
	{
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(html);
		} catch (IOException e) {
			log.error("显示text/html{}报错\n"+e,html);
			run_500();//返回500
			e.printStackTrace();
			
		}
		return;
	}
	
	/**
	 * application/json 格式的返回json
	 * @param json 返回的json
	 */
	protected void printJson(String json) {
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(json);
		} catch (IOException e) {
			log.error("返回application/json{}报错\n"+e,json);
			run_500();//返回500
			e.printStackTrace();
		}
		return;
	}
	


 
 
 /**
  * 500页面
  */
	 public void run_500() {
		 try {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
		} catch (IOException e) {
			
			e.printStackTrace();
		}//返回500
	}
 
	 /**
	  * 404页面
	  */
	 public void run_404() {
		 try {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	 /**
	  * 403页面
	  */
	 public void run_403() {
			
		 try {
			response.sendError(HttpServletResponse.SC_FORBIDDEN );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

}
