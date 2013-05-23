package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.app.entity.Medicine;
import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.DrugClassDao;
import cn.yi18.dao.DiseaseClassDao;
import cn.yi18.entity.DrugClass;
import cn.yi18.entity.DiseaseClass;

public class DiseaseClassService
{
	
	public List<DiseaseClass> getTree() {
		String fullyQualifiedName = "Diseases";
		Serializable key="tree_";
		List<DiseaseClass> list = (List<DiseaseClass>) EhCacheEngine.get(fullyQualifiedName, key);
		if (list==null)
		{
			DiseaseClassDao diseaseClassDao = new DiseaseClassDao();
			list = diseaseClassDao.getDiseaseClass();
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list; 
	}
	
	public List<Medicine> getMedicineClass() {
		DiseaseClassDao diseaseClassDao = new DiseaseClassDao();
		return diseaseClassDao.getMedicineClass();
	}

}
