package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.LinksDao;
import cn.yi18.dao.PartnerDao;
import cn.yi18.pojo.Links;
import cn.yi18.pojo.Partner;

public class PartnerService
{
	
	public List<Partner> getAll()
	{
		return partnerDao.getAll();
	}
	private PartnerDao partnerDao = new PartnerDao();
}
