package cn.yi18.app;


import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;


import cn.yi18.app.entity.AskApp;
import cn.yi18.app.entity.Drug;

import cn.yi18.app.entity.Medicine;
import cn.yi18.cache.VisitLogEhCache;

import cn.yi18.service.DrugClassService;
import cn.yi18.service.DrugService;

import cn.yi18.util.PageUtil;

/**
 * 药品信息的APP接口
 * classlist 分类
 * list 显示信息列表
 * show 详细信息
 * @author 陈磊
 *
 */
public class DrugApp extends BaseApp
{

	
	/**
	 * 显示分类信息
	 */
	public void classlist() 
	{
	
		AskApp ask = getAskApp();
		DrugClassService drugClassService = new DrugClassService();
		List<Medicine> list =  drugClassService.getMedicineClass();
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
		DrugService drugService = new DrugService();
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
		printJson(this.toJsonP(ask.getCallback(), json,total));
		
	
		
	}
	
	
	
	
	/**
	 * 显示药品信息
	 * 参数 id 
	 */
	public void show() 
	{
		AskApp ask = getAskApp();
		DrugService drugService = new DrugService();
		Drug drug = drugService.getDrug(ask.getId());
		if(drug==null){
			run_false();
			return;
		}//如果不存在就返回404页面

		VisitLogEhCache.Add(drug.getId(), "yi18_drug");//更新阅读数
		
		Gson gson = new Gson();
		String json=gson.toJson(drug);
		printJson(this.toJsonP(ask.getCallback(), json));

		
	}
	
	

	
}
