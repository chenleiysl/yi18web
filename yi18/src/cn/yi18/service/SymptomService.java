package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Symptoms bean = new Symptoms();
		String filter = " allow =  "+SymptomEnum.Check_Status.IsCheck.getValue();
		return (List<Symptoms>) bean.filter(filter , 1, size);
	}

	
	public List<Symptoms> getHot(int page,int size) 
	{
		SymptomDao symptomDao = new SymptomDao();
		return  symptomDao.getHot(page, size);
	}
	
	public List<Symptoms> getHot(int page,int size,long id) 
	{
		SymptomDao symptomDao = new SymptomDao();
		return  symptomDao.getHot(page, size,id);
	}
	
	public PageUtil getPageHot(int page, int size) 
	{
		Symptoms bean = new Symptoms();
		String filter =  " allow = "+SymptomEnum.Check_Status.IsCheck.getValue();
		int total = bean.totalCount(filter);//取得总数
		return new PageUtil(getHot(page, size), page, size, total);
	}

	public List<Symptoms> getNew(int size, long id) {
		
		Symptoms bean = new Symptoms();
		String filter = " allow =  "+SymptomEnum.Check_Status.IsCheck.getValue() +" AND symptomsclass="+id;
		return (List<Symptoms>) bean.filter(filter , 1, size);
	}

	public PageUtil getPageHot(int page, int size, long id) {
		Symptoms bean = new Symptoms();
		String filter =  " allow = "+SymptomEnum.Check_Status.IsCheck.getValue()+" AND symptomsclass="+id;
		int total = bean.totalCount(filter);//取得总数
		return new PageUtil(getHot(page, size,id), page, size, total);
	}
	

	
}
