package cn.yi18.service;

import java.util.List;

import cn.yi18.dao.DrugInfoDao;
import cn.yi18.entity.DrugInfo;
import cn.yi18.enums.DrugEnum;
import cn.yi18.pojo.Druginfo;

public class DrugInfoService {

	private DrugInfoDao drugInfoDao = new DrugInfoDao();
	
	public List<DrugInfo> getDrugInfo(long id)
	{
		return drugInfoDao.getDrugInfo(id);
	}
	
	
	
}
