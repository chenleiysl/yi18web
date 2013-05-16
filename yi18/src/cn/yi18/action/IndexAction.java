package cn.yi18.action;

import java.io.IOException;
import java.io.Serializable;


import javax.servlet.ServletException;



import cn.yi18.cache.EhCacheEngine;
import cn.yi18.entity.Index;

import cn.yi18.service.DiseaseService;

import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.service.SymptomService;


/**
 * 首页信息
 * @author 陈磊
 *
 */
public class IndexAction extends BaseAction
{
	@Override
	public void execute() throws ServletException, IOException {

		root.put("index", _getIndex());
		root.put("title", "医药吧 中国医药信息库网站");
		printFreemarker("default/index.ftl", root);
		
		
		
	}
	
	public void index()
	{
		root.put("index", _getIndex());
		root.put("title", "医药吧 中国医药信息库网站");
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
