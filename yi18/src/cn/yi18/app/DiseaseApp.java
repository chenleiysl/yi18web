package cn.yi18.app;


import java.util.ArrayList;
import java.util.List;




import com.google.gson.Gson;


import cn.yi18.app.entity.AskApp;
import cn.yi18.app.entity.Disease;

import cn.yi18.app.entity.Medicine;
import cn.yi18.cache.VisitLogEhCache;

import cn.yi18.service.DiseaseClassService;
import cn.yi18.service.DiseaseService;

import cn.yi18.util.PageUtil;

/**
 * 显示疾病信息
 * @author 陈磊
 *
 */
public class DiseaseApp extends BaseApp
{

	/**
	 * 显示分类信息
	 */
	public void classlist() 
	{
	
		AskApp ask = getAskApp();
		DiseaseClassService diseaseClassService = new DiseaseClassService();
		List<Medicine> list =  diseaseClassService.getMedicineClass();
		Gson gson = new Gson();
		String json=gson.toJson(list);
		
		printJson(this.toJsonP(ask.getCallback(), json));
	}
	
	
	/**
	 * 显示分类列表信息
	 * 主要参数 分类id
	 * page 当前页
	 * limit 页显示条数
	 * 
	 */
	public void list() 
	{
		AskApp ask = getAskApp();
		
		int total = 0;
		List<Disease> list = new ArrayList<Disease>();
		DiseaseService diseaseService = new DiseaseService();
		
		PageUtil page = null;
		if(ask.getId()==0)//默认不分类
		{
			page = diseaseService.getPageHot(ask.getPage(), ask.getLimit());	
			
		}else
		{
			page =  diseaseService.getPageHot(ask.getPage(), ask.getLimit(),ask.getId());
			
			
		}
		
		total=page.getTotal();
		@SuppressWarnings("unchecked")
		List<cn.yi18.pojo.Disease> pagelist = (List<cn.yi18.pojo.Disease>) page.getList();
		for (cn.yi18.pojo.Disease pagedisease : pagelist) 
		{
			Disease disease = new Disease();
			disease.setId(pagedisease.getId());	
			disease.setCount(pagedisease.getCount());
			disease.setName(pagedisease.getName());
			list.add(disease);
		}

		Gson gson = new Gson();
		
		String json=gson.toJson(list);
		
	
		printJson(this.toJsonP(ask.getCallback(), json, total));

		
	}
	
	/**
	 * 显示疾病信息
	 * 参数 id 
	 */
	public void show()
	{
		AskApp ask = getAskApp();
		DiseaseService diseaseService = new DiseaseService();
		Disease disease= diseaseService.getDisease(ask.getId());
		if(disease==null){
			run_false();
			return;
		}//如果不存在就返回404页面
		
		VisitLogEhCache.Add(disease.getId(), "yi18_disease");//更新阅读数
		Gson gson = new Gson();
		String json=gson.toJson(disease);
		
		printJson(this.toJsonP(ask.getCallback(), json));

		
	}
	
	
}
