package cn.yi18.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.app.entity.Symptom;
import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.DrugDao;
import cn.yi18.dao.SymptomDao;
import cn.yi18.enums.DrugEnum;
import cn.yi18.enums.SymptomEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.lucene.DrugLucene;
import cn.yi18.lucene.IndexFiles;
import cn.yi18.lucene.PageInfo;
import cn.yi18.lucene.SymptomLucene;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Symptominfo;
import cn.yi18.pojo.Symptoms;
import cn.yi18.util.JsoupUtil;
import cn.yi18.util.PageUtil;

/**
 * 
 * @author 陈磊
 *
 */
public class SymptomService 
{
	
	
	public List<Symptoms> getNoCheck()
	{
		Symptoms bean = new Symptoms();
		String filter ="allow ="+ SymptomEnum.Check_Status.NoCheck.getValue();
		return (List<Symptoms>) bean.filter(filter, 1, 20);
		
	}
	
	public void upadte(Symptoms symptoms ,List<Symptominfo> list)
	{
		
		String content = "";//搜索存放数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allow", symptoms.getAllow());
		map.put("name", symptoms.getName());
		map.put("description", symptoms.getDescription());
		map.put("symptomsclass", symptoms.getSymptomsclass());
		map.put("time", new Timestamp(new Date().getTime()));//同时也更新时间
		symptoms.update(map , symptoms.getId());
		content=content+symptoms.getDescription();
		for (Symptominfo symptominfo : list) {
			map.clear();
			map.put("message", symptominfo.getMessage());
			symptominfo.update(map, symptominfo.getId());
			content=content+JsoupUtil.Text(symptominfo.getMessage());
		}
		
		IndexFiles indexFiles = new SymptomLucene();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTitle( symptoms.getName());
		pageInfo.setId(symptoms.getId());
		pageInfo.setUrl("symptom/show/"+symptoms.getId());
		pageInfo.setContent(content);
		indexFiles.create(pageInfo );
	}

	
	public List<Symptoms> getNew(int size) 
	{
		String fullyQualifiedName = "Symptomses";
		Serializable key="new_"+size;
		List<Symptoms> list = (List<Symptoms>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			Symptoms bean = new Symptoms();
			String filter = " allow =  "+SymptomEnum.Check_Status.IsCheck.getValue();
			list= (List<Symptoms>) bean.filter(filter , 1, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		
		return list;
	}

	
	public List<Symptoms> getHot(int page,int size) 
	{
		String fullyQualifiedName = "Symptomses";
		Serializable key="hot_"+page+"and"+size;
		List<Symptoms> list = (List<Symptoms>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			SymptomDao symptomDao = new SymptomDao();
			list=  symptomDao.getHot(page, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		
		return  list;
	}
	
	public List<Symptoms> getHot(int page,int size,long id) 
	{
		String fullyQualifiedName = "Symptomses";
		Serializable key="hot_"+page+"and"+size+"and"+id;
		List<Symptoms> list = (List<Symptoms>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			SymptomDao symptomDao = new SymptomDao();
			list= symptomDao.getHot(page, size,id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		
		return  list;
	}
	
	public PageUtil getPageHot(int page, int size) 
	{
		String fullyQualifiedName = "Symptomses";
		Serializable key="pagehot_"+page+"and"+size;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Symptoms bean = new Symptoms();
			String filter =  " allow = "+SymptomEnum.Check_Status.IsCheck.getValue();
			int total = bean.totalCount(filter);//取得总数
			pageUtil= new PageUtil(getHot(page, size), page, size, total);
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		
		return pageUtil;
	}

	public List<Symptoms> getNew(int size, long id) {
		
		String fullyQualifiedName = "Symptomses";
		Serializable key="new_"+size+"and"+id;
		List<Symptoms> list = (List<Symptoms>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			Symptoms bean = new Symptoms();
			String filter = " allow =  "+SymptomEnum.Check_Status.IsCheck.getValue() +" AND symptomsclass="+id;
			list=(List<Symptoms>) bean.filter(filter , 1, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}

	public PageUtil getPageHot(int page, int size, long id) {
		String fullyQualifiedName = "Symptomses";
		Serializable key="pagehot_"+page+"and"+size+"and"+id;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Symptoms bean = new Symptoms();
			String filter =  " allow = "+SymptomEnum.Check_Status.IsCheck.getValue()+" AND symptomsclass="+id;
			int total = bean.totalCount(filter);//取得总数
			pageUtil= new PageUtil(getHot(page, size,id), page, size, total);
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		return pageUtil;
	}
	
	
	public Symptom getSymptom( long id) 
	{
		SymptomDao symptomDao = new SymptomDao();
		return symptomDao.getSymptom(id);
	}

	
}
