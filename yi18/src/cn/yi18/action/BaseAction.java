package cn.yi18.action;

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
			run_404();
			e.printStackTrace();
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
				request.getRequestDispatcher(uri).forward(request, response);
			} catch (ServletException e) {
				run_500();//返回500
				e.printStackTrace();
			} catch (IOException e) {
				run_500();//返回500
				e.printStackTrace();
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
	 *    通过Freemarker方式显示
	 * @param ftl ftl文件
	 * @param root 数据
	 */
 protected void printFreemarker(String ftl,Map<String,Object> root)
  {
	 
	//HttpSession session = new SessionContext( request);
	 if(root.get("basePath")==null) root.put("basePath", request.basePath());
	 if(root.get("url")==null)  root.put("url", Base64Coder.encodeUrlBase64(request.getRequestURL().toString())); 
	 if(root.get("session_id")==null) root.put("session_id", request.getSession().getId()); 
	 if(root.get("user")==null) root.put("user", session.getUser(yi18_id));
	
	 //设置默认信息
	 if(root.get("title")==null)  root.put("title", "医药吧   中国医药信息查询平台");
	 if(root.get("keywords")==null)  root.put("keywords", "药品信息、病状查找、疾病诊断、健康知识、综合资讯的综合信息网站 医药吧中国医药信息网");
	 if(root.get("description")==null)  root.put("description", " 医药吧 中国医药信息查询平台www.yi18.cn 成立于2013年3月，是一个个人工作室开发医药辅助网站。我们传播医药技术，推广医药产品，发现最新疾病，整理病状信息，提供健康知识，传递新闻信息。为广大群众提供一个交流、展示、查询的医药性综合平台,同时打造成中国最大的医药数据库中心。经过不断的改进，目前医药吧已经形成了由药品信息、病状信息、疾病信息、健康知识和综合咨询等几大频道内容。");
	 if(root.get("author")==null)  root.put("author", "yi18.cn");
	
	 root.put("links", _getLinks());
	 
	 try {
		Template  t = cfg.getTemplate(ftl);
		t.setEncoding("UTF-8");
		response.setContentType("text/html; charset=" + t.getEncoding());
		Writer out = response.getWriter();
		t.process(root, out);
	
	} catch (IOException  e) {
		log.error("通过Freemarker文件{}错误",ftl);
		run_500();//返回500
		e.printStackTrace();
	} catch (TemplateException e) {
		run_500();//返回500
		e.printStackTrace();
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
 
 
 /**
  * 500页面
  */
	 public void run_500() {
		 try {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
