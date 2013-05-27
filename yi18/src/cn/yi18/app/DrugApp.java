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
import cn.yi18.app.entity.Drug;
import cn.yi18.app.entity.Lore;
import cn.yi18.app.entity.Medicine;
import cn.yi18.pojo.Loreclass;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.service.DrugClassService;
import cn.yi18.service.DrugService;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;

public class DrugApp extends BaseApp
{

	
	public void classlist() 
	{
	
		AskApp ask = getAskApp();
		DrugClassService drugClassService = new DrugClassService();
		List<Medicine> list =  drugClassService.getMedicineClass();
		Gson gson = new Gson();
		String json=gson.toJson(list);
		json=ask.getCallback()+"({\"success\": true, \"yi18\":"+json+"})";
		printJson(json);
	}
	
	
	
	public void list() 
	{
		AskApp ask = getAskApp();
		
		int total = 0;
		List<Drug> list = new ArrayList<Drug>();
		PageUtil page = null;
		if(ask.getId()==0)//默认不分类
		{
			page = drugService.getPageHot(ask.getPage(), ask.getLimit());	
			
		}else
		{
			page =  drugService.getPageHot(ask.getPage(), ask.getLimit(),ask.getId());
			
			
		}
		
		total=page.getTotal();
		@SuppressWarnings("unchecked")
		List<cn.yi18.pojo.Drug> pagelist = (List<cn.yi18.pojo.Drug>) page.getList();
		for (cn.yi18.pojo.Drug pagedrug : pagelist) 
		{
			Drug drug = new Drug();
			drug.setId(pagedrug.getId());
			drug.setImage(pagedrug.getImage());
			drug.setCount(pagedrug.getCount());
			drug.setAlias(pagedrug.getAlias());
			drug.setName(pagedrug.getName());
			list.add(drug);
		}

		Gson gson = new Gson();
		
		String json=gson.toJson(list);
		
		json=ask.getCallback()+"({\"success\": true,  \"total\":"+total+",\"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	
	
	
	
	
	public void show() throws IllegalAccessException, InvocationTargetException
	{
		AskApp ask = getAskApp();
		Drug drug = drugService.getDrug(ask.getId());
		
	
		Gson gson = new Gson();
		String json=gson.toJson(drug);
		json=ask.getCallback()+"({\"success\": true, \"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	

	
	
	
	private DrugService drugService = new DrugService();
	
}