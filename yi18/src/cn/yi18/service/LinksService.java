package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.LinksDao;
import cn.yi18.pojo.Links;

public class LinksService
{
	
	public List<Links> getAll()
	{
		return linksDao.getAll();
	}
	private LinksDao linksDao = new LinksDao();
}
