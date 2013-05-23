package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.app.entity.Medicine;
import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.DrugClassDao;
import cn.yi18.entity.DrugClass;

public class DrugClassService
{
	
	public List<DrugClass> getTree() {
		
		String fullyQualifiedName="Drugs";
		Serializable key="tree_";
		List<DrugClass> list=(List<DrugClass>) EhCacheEngine.get(fullyQualifiedName, key);
		if (list==null)
		{
			list =drugClassDao.getDrugClass();
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list ;
	}
	
	public List<Medicine> getMedicineClass() {
		return drugClassDao.getMedicineClass();
	}
	private DrugClassDao drugClassDao = new DrugClassDao();
}
