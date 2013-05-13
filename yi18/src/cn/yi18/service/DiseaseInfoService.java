package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.DiseaseInfoDao;
import cn.yi18.dao.SymptomInfoDao;
import cn.yi18.entity.DiseaseInfo;
import cn.yi18.entity.SymptomInfo;




public class DiseaseInfoService {

	private DiseaseInfoDao diseaseInfoDao = new DiseaseInfoDao();
	
	/**
	 * 取得疾病的主要目录信息
	 * @param id
	 * @return
	 */
	public List<DiseaseInfo> getDiseaseInfo(long id)
	{
		String fullyQualifiedName="Disease";
		Serializable key = id+"_info";
		List<DiseaseInfo> list=(List<DiseaseInfo>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list=diseaseInfoDao.getDiseaseinfo(id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}
	
	
	
}
