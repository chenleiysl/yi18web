package cn.yi18.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.entity.Index;
import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Lore;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Symptoms;
import cn.yi18.pojo.User;
import cn.yi18.service.DiseaseService;
import cn.yi18.service.DrugService;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.service.SymptomService;
import cn.yi18.util.PageUtil;

public class IndexAction extends BaseAction
{
	@Override
	public void execute() throws ServletException, IOException {
		//User bean = new User();
		// User user = bean.get(1);
		
//		List<News> news = newsService.getNews(10);
//		root.put("news", news);//综合信息
//		List<Lore> week = loreService.getHot(1, 10, 7);//最近7天
//		List<Lore> month = loreService.getHot(1, 10, 30);//最近30天
//		 root.put("week", week);
//		 root.put("month", month);
//		 List<Disease> dnews = diseaseService.getNew(10);
//		 List<Symptoms> snews = symptomService.getNew(10);
//			
//			root.put("snews", snews);
//			root.put("dnews", dnews);
			root.put("index", _getIndex());
		printFreemarker("default/index.ftl", root);
		//printHtml("欢迎来到医药吧，我们将于2013年6月正式推出，敬请期待！");
		
		
	}
	
	public void index()
	{
		printFreemarker("default/index.ftl", root);
	}
	
	/**
	 * 关于我们
	 */
	public void about()
	{
		printFreemarker("default/about.ftl", root);
	}

	/**
	 * 合作伙伴
	 */
	public void partner()
	{
		root.put("title", "合作伙伴|医药吧" );
		printFreemarker("default/partner.ftl", root);
	}
	
	
	/**
	 * 取得首页的数据，主要是做二级缓存，缓存时间2分钟
	 * @return
	 */
	private Index _getIndex()
	{
		//Index index = new Index();
		
		
		String fullyQualifiedName="Indexs";
		Serializable key = "index";
		Index index=(Index) EhCacheEngine.get(fullyQualifiedName, key);
		if(index==null)
		{
			index = new Index();
			index.setDnews(diseaseService.getNew(10));
			index.setSnews(symptomService.getNew(10));
			index.setMonth(loreService.getHot(1, 10, 30));
			index.setWeek(loreService.getHot(1, 10, 7));
			index.setNews(newsService.getNews(10));
			
			EhCacheEngine.add(fullyQualifiedName, key, index);
		}
		
		return index;
	}
	
	private NewsService newsService = new NewsService();
	private LoreService loreService = new LoreService();
	private DiseaseService diseaseService = new DiseaseService();
	private SymptomService  symptomService = new SymptomService();
}
