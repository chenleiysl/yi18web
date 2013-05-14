package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.DrugInfoDao;
import cn.yi18.entity.DrugInfo;
import cn.yi18.enums.DrugEnum;
import cn.yi18.pojo.Druginfo;

public class DrugInfoService {

	private DrugInfoDao drugInfoDao = new DrugInfoDao();
	
	public List<DrugInfo> getDrugInfo(long id)
	{
		String fullyQualifiedName="Drug";
		Serializable key = id+"_info";
		List<DrugInfo> list=(List<DrugInfo>) EhCacheEngine.get(fullyQualifiedName, key );
		if (list==null)
		{
			list=drugInfoDao.getDrugInfo(id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}
	
	
	
}
