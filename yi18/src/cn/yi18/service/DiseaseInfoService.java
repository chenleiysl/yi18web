package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.DiseaseInfoDao;
import cn.yi18.dao.SymptomInfoDao;
import cn.yi18.entity.DiseaseInfo;
import cn.yi18.entity.SymptomInfo;




public class DiseaseInfoService {

	private DiseaseInfoDao diseaseInfoDao = new DiseaseInfoDao();
	
	public List<DiseaseInfo> getDiseaseInfo(long id)
	{
		return diseaseInfoDao.getDiseaseinfo(id);
	}
	
	
	
}
