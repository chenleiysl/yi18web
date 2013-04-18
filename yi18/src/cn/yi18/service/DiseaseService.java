package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.dao.DrugDao;
import cn.yi18.dao.SymptomDao;
import cn.yi18.enums.DiseaseEnum;
import cn.yi18.enums.DrugEnum;
import cn.yi18.enums.SymptomEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Diseaseinfo;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Symptominfo;
import cn.yi18.pojo.Symptoms;
import cn.yi18.util.PageUtil;

/**
 * 
 * @author 陈磊
 *
 */
public class DiseaseService 
{
	
	
	public List<Disease> getNoCheck()
	{
		Disease bean = new Disease();
		String filter ="allow ="+ DiseaseEnum.Check_Status.NoCheck.getValue();
		return (List<Disease>) bean.filter(filter, 1, 20);
		
	}
	
	public void upadte(Disease symptoms ,List<Diseaseinfo> list)
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allow", symptoms.getAllow());
		map.put("name", symptoms.getName());
		map.put("description", symptoms.getDescription());
		map.put("diseaseclass", symptoms.getDiseaseclass());
		symptoms.update(map , symptoms.getId());
		for (Diseaseinfo diseaseinfo : list) {
			map.clear();
			map.put("message", diseaseinfo.getMessage());
			diseaseinfo.update(map, diseaseinfo.getId());
		}
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
