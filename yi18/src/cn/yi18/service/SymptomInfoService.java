package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.SymptomInfoDao;
import cn.yi18.entity.SymptomInfo;




public class SymptomInfoService {

	private SymptomInfoDao symptomInfoDao = new SymptomInfoDao();
	
	public List<SymptomInfo> getSymptomInfo(long id)
	{
		return symptomInfoDao.getSymptomInfo(id);
	}
	
	
	
}
