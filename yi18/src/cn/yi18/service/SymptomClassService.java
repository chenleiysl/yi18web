package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.DrugClassDao;
import cn.yi18.dao.SymptomClassDao;
import cn.yi18.entity.DrugClass;
import cn.yi18.entity.SymptomClass;

public class SymptomClassService
{
	
	public List<SymptomClass> getTree() {
		return symptomClassDao.getSymptomClass();
	}
	private SymptomClassDao symptomClassDao = new SymptomClassDao();
}
