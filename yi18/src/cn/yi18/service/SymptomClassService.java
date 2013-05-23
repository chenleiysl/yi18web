package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.app.entity.Medicine;
import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.DrugClassDao;
import cn.yi18.dao.SymptomClassDao;
import cn.yi18.entity.DrugClass;
import cn.yi18.entity.SymptomClass;

public class SymptomClassService
{
	
	public List<SymptomClass> getTree() {
		
		String fullyQualifiedName = "Symptomses";
		Serializable key="tree_";
		List<SymptomClass> list=(List<SymptomClass>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			
			list= symptomClassDao.getSymptomClass();
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list; 
	
	}
	private SymptomClassDao symptomClassDao = new SymptomClassDao();
	
	
	public List<Medicine> getMedicineClass() {
		return symptomClassDao.getMedicineClass();
	}
}
