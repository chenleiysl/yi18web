package cn.yi18.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

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
		
		List<News> news = newsService.getNews(10);
		root.put("news", news);//综合信息
		List<Lore> week = loreService.getHot(1, 10, 7);//最近7天
		List<Lore> month = loreService.getHot(1, 10, 30);//最近30天
		 root.put("week", week);
		 root.put("month", month);
		 List<Disease> dnews = diseaseService.getNew(10);
		 List<Symptoms> snews = symptomService.getNew(10);
			
			root.put("snews", snews);
			root.put("dnews", dnews);
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
		printFreemarker("default/partner.ftl", root);
	}
	
	
	private NewsService newsService = new NewsService();
	private LoreService loreService = new LoreService();
	private DiseaseService diseaseService = new DiseaseService();
	private SymptomService  symptomService = new SymptomService();
}
