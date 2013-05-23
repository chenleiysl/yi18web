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
import cn.yi18.app.entity.Lore;
import cn.yi18.app.entity.Medicine;
import cn.yi18.pojo.Loreclass;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.service.DiseaseClassService;
import cn.yi18.service.DrugClassService;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;

public class DiseaseApp extends BaseApp
{

	
	public void classlist() throws IllegalAccessException, InvocationTargetException
	{
	
		Map<?, ?> map = request.getParameterMap();
		AskApp ask = new AskApp();
		BeanUtils.populate(ask, map);
		DiseaseClassService diseaseClassService = new DiseaseClassService();
		List<Medicine> list =  diseaseClassService.getMedicineClass();
		Gson gson = new Gson();
		String json=gson.toJson(list);
		json=ask.getCallback()+"({\"success\": true, \"medicine\":"+json+"})";
		printJson(json);
	}
	
	
	
}
