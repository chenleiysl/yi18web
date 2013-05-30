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


import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yi18.app.entity.AskApp;
import cn.yi18.enums.CookieEnum;
import cn.yi18.http.*;

import cn.yi18.pojo.Links;

import cn.yi18.service.LinksService;
import cn.yi18.util.Base64Coder;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 所有的App类的父类
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
					run_false();
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					log.error("{} 类中 没有实现 {}方法 \n" + e,this.getClass(),action);
					run_false();//返回500
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					log.error("{} 类中 没有实现 {}方法 \n" + e,this.getClass(),action);
					run_false();//返回404
					e.printStackTrace();
				} catch (SecurityException e) {
					log.error("{} 类中 没有实现 {}方法 \n" + e,this.getClass(),action);
					run_false();//返回500
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
	 * application/json 格式的返回json
	 * @param json 返回的json
	 */
	protected void printJson(String json) {
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(json);
		} catch (IOException e) {
			log.error("返回application/json{}报错\n"+e,json);
			run_false();//返回500
			e.printStackTrace();
		}
		return;
	}
	
	

 
 
 
	 /**
	  * 404页面
	  */
	 public void run_false() {
		
		 String json="{\"success\": false}";
		String callback = this.getAskApp().getCallback();
		printJson(this.toJsonP(callback , json));
	}
	 
	
	 

	 /**
	  * 主要处理每次APP接口传输的参数
	  * 转成AskApp
	  * @return 
	  */
		protected AskApp getAskApp()
		{
			Map<?, ?> map = request.getParameterMap();
			AskApp ask = new AskApp();
			try {
				BeanUtils.populate(ask, map);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ask;
		}
		
		/**
		 * 这里返回的数据主要有JSonp了Json两种处理方式
		 */
		public String toJsonP(String callback,String json)
		{
			if(callback==null)
			{
				json="{\"success\": true, \"yi18\":"+json+"}";
			}else
			{
				json=callback+"({\"success\": true, \"yi18\":"+json+"})";
			}
			return json;
		}
		public String toJsonP(String callback,String json,int total)
		{
			if(callback==null)
			{
				json="{\"success\": true,  \"total\":"+total+",\"yi18\":"+json+"}";
			}else
			{
				json=callback+"({\"success\": true,  \"total\":"+total+", \"yi18\":"+json+"})";
			}
			return json;
		}

}
