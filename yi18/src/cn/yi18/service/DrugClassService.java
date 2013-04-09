package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.DrugClassDao;
import cn.yi18.entity.DrugClass;

public class DrugClassService
{
	
	public List<DrugClass> getTree() {
		return drugClassDao.getDrugClass();
	}
	private DrugClassDao drugClassDao = new DrugClassDao();
}
