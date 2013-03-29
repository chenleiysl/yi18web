package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.DrugInfoDao;
import cn.yi18.entity.DrugInfo;

public class DrugInfoService {

	private DrugInfoDao drugInfoDao = new DrugInfoDao();
	
	public List<DrugInfo> getDrugInfo(long id)
	{
		return drugInfoDao.getDrugInfo(id);
	}
}
