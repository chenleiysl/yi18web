package cn.yi18.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.yi18.pojo.News;



/**
 * 
 * @author 陈磊，综合资讯
 *
 */
public class NewsAction extends BaseAction
{
	public void add() throws IllegalAccessException, InvocationTargetException
	{
		if (request.isSubmit()) 
		{
			News bean = new News();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			bean.save();
			
			//sendRedirect(request.basePath()+"news/list");
			sendRedirect(request.basePath()+"news/add");
		}
		else 
		{
			printFreemarker("default/add_news.ftl", root);
			
		}
		
	}

}
