package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.LinksDao;
import cn.yi18.dao.PartnerDao;
import cn.yi18.pojo.Links;
import cn.yi18.pojo.Partner;

public class PartnerService
{
	
	public List<Partner> getAll()
	{
		String fullyQualifiedName="Partner";
		Serializable key="partenes";
		List<Partner> list =(List<Partner>) EhCacheEngine.get(fullyQualifiedName, key);
		if (list==null)
		{
			PartnerDao partnerDao = new PartnerDao();
			list=partnerDao.getAll();
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}
	
}
