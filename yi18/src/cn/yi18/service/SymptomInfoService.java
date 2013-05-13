package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.SymptomInfoDao;
import cn.yi18.entity.SymptomInfo;




public class SymptomInfoService {

	private SymptomInfoDao symptomInfoDao = new SymptomInfoDao();
	
	/**
	 * 取得病状的id相关目录相关信息
	 * @param id
	 * @return
	 */
	public List<SymptomInfo> getSymptomInfo(long id)
	{
		
		String fullyQualifiedName="Disease";
		Serializable key = id+"_info";
		List<SymptomInfo> list=(List<SymptomInfo>) EhCacheEngine.get(fullyQualifiedName , key );
		if(list==null)
		{
			list=symptomInfoDao.getSymptomInfo(id);
			EhCacheEngine.add(fullyQualifiedName, key , list);
		}
		return list;
	}
	
	
	
}
