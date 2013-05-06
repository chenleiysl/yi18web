package cn.yi18.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.yi18.enums.NewsEnum;
import cn.yi18.lucene.IndexFiles;
import cn.yi18.lucene.LoreLucene;
import cn.yi18.lucene.NewsLucene;
import cn.yi18.lucene.PageInfo;
import cn.yi18.pojo.Lore;
import cn.yi18.pojo.Loreclass;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.util.JsoupUtil;
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
			root.put("title", "添加健康知识 |医药吧 ");
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
			 
			 Loreclass loreclass= new Loreclass();
			 loreclass=loreclass.get(id);
			 root.put("loreclassid", id);
			 root.put("loreclass", loreclass.getName());
			 root.put("title", loreclass.getName()+"|健康知识_医药吧");
			root.put("keywords", loreclass.getName());
			root.put("description",loreclass.getDescription());
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
		
		Loreclass loreclass= new Loreclass();
		loreclass=loreclass.get(lore.getLoreclass());
		root.put("lore", lore);
		root.put("loreclass", loreclass);
		root.put("title", lore.getTitle()+"|综合信息_医药吧");
		root.put("keywords", lore.getTitle());
		root.put("description",lore.subMessage(80));
		printFreemarker("default/lore.ftl", root);
	}
	
    /**
     * 更新健康知识
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * 
     */
    public void update() throws IllegalAccessException, InvocationTargetException 
	{
    	Lore bean = new Lore();
		Map map = request.getParameterMap();
		BeanUtils.populate(bean, map);
		
		Map<String, Object> vmap = new HashMap<String, Object>();
		vmap.put("title", bean.getTitle());
		vmap.put("loreclass", bean.getLoreclass());
		vmap.put("message", bean.getMessage());
		vmap.put("author", bean.getAuthor());
		vmap.put("allow", NewsEnum.Check_Status.IsCheck.getValue());
		bean.update(vmap , bean.getId());
		
		/**
		 * 存储
		 */
		IndexFiles indexFiles = new LoreLucene();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTitle( bean.getTitle());
		pageInfo.setId(bean.getId());
		pageInfo.setUrl("lore/show/"+bean.getId());
		String content = JsoupUtil.Text(bean.getMessage());
		pageInfo.setContent(content);
		indexFiles.create(pageInfo );
		
		sendRedirect(request.basePath()+"admin/lore");
		
	}
	private LoreService loreService = new LoreService();
	
	

}
