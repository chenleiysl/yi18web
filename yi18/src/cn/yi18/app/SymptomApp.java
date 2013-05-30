package cn.yi18.app;


import java.util.ArrayList;
import java.util.List;



import com.google.gson.Gson;


import cn.yi18.app.entity.AskApp;

import cn.yi18.app.entity.Medicine;
import cn.yi18.app.entity.Symptom;
import cn.yi18.cache.VisitLogEhCache;

import cn.yi18.service.SymptomClassService;
import cn.yi18.service.SymptomService;
import cn.yi18.util.PageUtil;

/**
 * 病状信息app
 * @author 陈磊
 *
 */
public class SymptomApp extends BaseApp
{

	/**
	 * 显示分类信息
	 */
	public void classlist() 
	{
	
		
		AskApp ask = getAskApp();
		
		SymptomClassService symptomClassService = new SymptomClassService();
		List<Medicine> list =  symptomClassService.getMedicineClass();
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
		List<Symptom> list = new ArrayList<Symptom>();
		SymptomService symptomService = new SymptomService();
		
		PageUtil page = null;
		if(ask.getId()==0)//默认不分类
		{
			page = symptomService.getPageHot(ask.getPage(), ask.getLimit());	
			
		}else
		{
			page =  symptomService.getPageHot(ask.getPage(), ask.getLimit(),ask.getId());
			
			
		}
		
		total=page.getTotal();
		@SuppressWarnings("unchecked")
		List<cn.yi18.pojo.Symptoms> pagelist = (List<cn.yi18.pojo.Symptoms>) page.getList();
		for (cn.yi18.pojo.Symptoms pagesymptom : pagelist) 
		{
			Symptom symptom = new Symptom();
			symptom.setId(pagesymptom.getId());	
			symptom.setCount(pagesymptom.getCount());
			symptom.setName(pagesymptom.getName());
			list.add(symptom);
		}

		Gson gson = new Gson();
		
		String json=gson.toJson(list);
		
		
		printJson(this.toJsonP(ask.getCallback(), json, total));

		
	}
	/**
	 * 显示病状信息
	 * 参数 id 
	 */
	public void show() 
	{
		AskApp ask = getAskApp();
		SymptomService symptomService = new SymptomService();
		Symptom symptom = symptomService.getSymptom(ask.getId());
		if(symptom==null){
			run_false();//如果不存在就返回404页面
			return;
		}
		
		VisitLogEhCache.Add(symptom.getId(), "yi18_symptoms");//更新阅读数
		Gson gson = new Gson();
		String json=gson.toJson(symptom);
		
		printJson(this.toJsonP(ask.getCallback(), json));

		
	}
	
	
	
}
