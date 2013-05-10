package cn.yi18.service;

import java.io.Serializable;
import java.util.List;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.LinksDao;
import cn.yi18.pojo.Links;

public class LinksService
{
	
	/**
	 * 取得友情链接
	 * @return
	 */
	public List<Links> getAll()
	{
		
		 String fullyQualifiedName="Links";
			Serializable key="link";
			List<Links> list= (List<Links>) EhCacheEngine.get(fullyQualifiedName, key);
			if (list==null) {
				LinksDao linksDao = new LinksDao();
				 list=linksDao.getAll();
				 EhCacheEngine.add(fullyQualifiedName, key, list);
			}
		return list;
	}

}
