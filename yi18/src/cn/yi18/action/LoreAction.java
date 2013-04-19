package cn.yi18.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.yi18.pojo.Lore;
import cn.yi18.pojo.Loreclass;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;



/**
 * 
 * @author 陈磊，综合资讯
 *
 */
public class LoreAction extends BaseAction
{
	public void add() throws IllegalAccessException, InvocationTargetException
	{
		if (request.isSubmit()) 
		{
			Lore bean = new Lore();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			bean.save();
			
			//sendRedirect(request.basePath()+"news/list");
			root.put("message", "添加成功！等待审核！");
			printFreemarker("default/message.ftl", root);
		}
		else 
		{
			printFreemarker("default/add_lore.ftl", root);
			
		}
		
	}
	
	public void list() {
		
		int page= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		// 取得页面，如果没有默认为1
		if(request.getParams()==null)
		{
		List<Lore> week = loreService.getHot(1, 10, 7);//最近7天
		List<Lore> month = loreService.getHot(1, 10, 30);//最近30天
		 PageUtil news = loreService.getNews(page, 15);
		 root.put("week", week);
		 root.put("month", month);
		 root.put("page", news);
		}else {
			String sid= request.getParams()[0];
			Long id= Long.parseLong(sid);
			List<Lore> week = loreService.getHot(id,1, 10, 7);//最近7天
			List<Lore> month = loreService.getHot(id,1, 10, 30);//最近30天
			 PageUtil news = loreService.getNews(id,page, 15);
			 root.put("week", week);
			 root.put("month", month);
			 root.put("page", news);
		}
		 Loreclass bean = new Loreclass();
		 List< Loreclass> list = (List<Loreclass>) bean.list();
		 root.put("list", list);
		printFreemarker("default/lore_list.ftl", root);
	}
	
	public void show()
	{
		String sid= request.getParams()[0];
		Lore lore = new Lore();
		lore = lore.get(Long.parseLong(sid));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", lore.getCount()+1);
		lore.update(map , lore.getId()); //更新阅读次数
		
		root.put("lore", lore);
		printFreemarker("default/lore.ftl", root);
	}
	
	private LoreService loreService = new LoreService();
	
	

}
