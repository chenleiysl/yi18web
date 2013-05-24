package cn.yi18.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import cn.yi18.app.entity.AskApp;
import cn.yi18.app.entity.Disease;
import cn.yi18.app.entity.Drug;
import cn.yi18.app.entity.Lore;
import cn.yi18.app.entity.Medicine;
import cn.yi18.pojo.Loreclass;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.service.DiseaseClassService;
import cn.yi18.service.DiseaseService;
import cn.yi18.service.DrugClassService;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;

public class DiseaseApp extends BaseApp
{

	
	public void classlist() throws IllegalAccessException, InvocationTargetException
	{
	
		AskApp ask = getAskApp();
		DiseaseClassService diseaseClassService = new DiseaseClassService();
		List<Medicine> list =  diseaseClassService.getMedicineClass();
		Gson gson = new Gson();
		String json=gson.toJson(list);
		json=ask.getCallback()+"({\"success\": true, \"yi18\":"+json+"})";
		printJson(json);
	}
	
	
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
		
		json=ask.getCallback()+"({\"success\": true,  \"total\":"+total+",\"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	
	public void show() throws IllegalAccessException, InvocationTargetException
	{
		AskApp ask = getAskApp();
		DiseaseService diseaseService = new DiseaseService();
		Disease disease= diseaseService.getDisease(ask.getId());
		
		Gson gson = new Gson();
		String json=gson.toJson(disease);
		json=ask.getCallback()+"({\"success\": true, \"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	
}
