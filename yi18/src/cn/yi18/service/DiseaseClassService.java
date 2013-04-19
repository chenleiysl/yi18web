package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.DrugClassDao;
import cn.yi18.dao.DiseaseClassDao;
import cn.yi18.entity.DrugClass;
import cn.yi18.entity.DiseaseClass;

public class DiseaseClassService
{
	
	public List<DiseaseClass> getTree() {
		return diseaseClassDao.getDiseaseClass();
	}
	private DiseaseClassDao diseaseClassDao = new DiseaseClassDao();
}
