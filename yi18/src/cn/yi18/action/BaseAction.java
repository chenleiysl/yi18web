package cn.yi18.action;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


import net.sf.ehcache.Ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.yi18.cache.EhCacheEngine;
import cn.yi18.enums.CookieEnum;
import cn.yi18.http.*;
import cn.yi18.jdbc.DBManager;
import cn.yi18.pojo.Links;
import cn.yi18.pojo.User;
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
public  abstract class BaseAction  
{

	private static final Logger log= LoggerFactory.getLogger(BaseAction.class);
	@SuppressWarnings("rawtypes")
	private static Class[] NO_ARGS_CLASS = new Class[0];
	private static Object[] NO_ARGS_OBJECT = new Object[0];
	protected HttpRequest request;
	protected HttpResponse response;
	protected ServletContext context;
	protected HttpSession session;
	protected Configuration cfg; 
	protected Map<String, Object> root = new HashMap<String, Object>();
	protected String yi18_id=null;
	
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
		yi18_id=_GetCookieID(request);
		cfg = new Configuration();
		
		cfg.setServletContextForTemplateLoading(
		   request.getSession().getServletContext(), "WEB-INF/templates");
		cfg.setDefaultEncoding("UTF-8");
		
		
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					log.error("{} 类中 没有实现 {}方法 \n" + e,this.getClass(),action);
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
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
		response.sendError(HttpServletResponse.SC_NOT_FOUND);//返回404
	
	}
	
	/**
	 * 跳转后浏览器地址栏变化<br>
	 * <p>
	 * 传值出去的话，只能在url中带parameter或者放在session中，无法使用request.setAttribute来传递。<br> 
	 * @param url 重定向访问的路径  http://127.0.0.1:8080/ape/index.html
	 */
	protected void sendRedirect(String url)
	{
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			log.error("访问的URL{}不存在！\n"+e,url);
			e.printStackTrace();
		}finally{
			DBManager.closeConnection(); //释放数据库连接到连接池中
		}
		return;
	}
	
	/**
	 * Servlet页面跳转的路径是相对路径。forward方式只能跳转到本web应用中的页面上。
	 *<br>
	 *	跳转后浏览器地址栏不会变化。
	 *<br>
	 *使用这种方式跳转，传值可以使用三种方法：url中带parameter，session，request.setAttribute
	 *
	 * @param uri 跳转的相对路径 如：input.jsp
	 */
	public void forward(String uri) {
		try {
			try {
				request.getRequestDispatcher(uri).forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (IOException e){
			
			e.printStackTrace();
		}finally{
			DBManager.closeConnection(); //释放数据库连接到连接池中
		}
		return;
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
			e.printStackTrace();
			
		}finally{
			DBManager.closeConnection(); //释放数据库连接到连接池中
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
			e.printStackTrace();
		}finally{
			DBManager.closeConnection(); //释放数据库连接到连接池中
		}
		return;
	}
	
	/**
	 *    通过Freemarker方式显示
	 * @param ftl ftl文件
	 * @param root 数据
	 */
 protected void printFreemarker(String ftl,Map<String,Object> root)
  {
	 
	//HttpSession session = new SessionContext( request);
	 root.put("basePath", request.basePath());
	 root.put("url", Base64Coder.encodeUrlBase64(request.getRequestURL().toString())); 
	 root.put("session_id", request.getSession().getId()); 
	 root.put("user", session.getUser(yi18_id));
	
	 root.put("links", _getLinks());
	
	 //设置默认信息
	 if(root.get("title")==null)  root.put("title", "医药吧");
	 if(root.get("keywords")==null)  root.put("keywords", "药品 查询 检验 搜集");
	 if(root.get("description")==null)  root.put("description", "一个药品查询系统，打造一流的药品网站和医学辅助网站。");
	 if(root.get("author")==null)  root.put("author", "yi18.cn");
	try {
		Template  t = cfg.getTemplate(ftl);
		t.setEncoding("UTF-8");
		response.setContentType("text/html; charset=" + t.getEncoding());
		Writer out = response.getWriter();
		
	   try {
		t.process(root, out);
	} catch (TemplateException e) {
		
		e.printStackTrace();
	}
	} catch (IOException  e) {
		log.error("通过Freemarker文件{}错误",ftl);
		e.printStackTrace();
	}finally{
		
		DBManager.closeConnection(); //释放数据库连接到连接池中
	}
      
  }
 
 
 /**
  * 取得友情链接，同时也对友情链接做缓存
  * @return
  */
 private List<Links> _getLinks() 
 {
	
	LinksService linksService = new LinksService();
	 return linksService.getAll();
	
 }

 public String _GetCookieID( HttpRequest request) {
	 Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
	 
	 if(cookies==null)
		 return null;
	 
	 for(Cookie cookie : cookies){
		 if(cookie.getName().equals(CookieEnum.yi18_id.toString()))
		 {
			 return cookie.getValue();
		 }
	   
	 }
	return null;
}

}
