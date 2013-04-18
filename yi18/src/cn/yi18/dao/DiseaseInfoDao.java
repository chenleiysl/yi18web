



package cn.yi18.dao;

import java.util.List;


import cn.yi18.entity.DiseaseInfo;

import cn.yi18.jdbc.QueryHelper;

public class DiseaseInfoDao 
{
	public List<DiseaseInfo> getDiseaseinfo(long id)
	{
		
		String sql = "SELECT directory.title,diseaseinfo.message,diseaseinfo .id FROM "+
				"yi18_directory directory JOIN yi18_diseaseinfo diseaseinfo  "+
				"ON diseaseinfo.directory =directory.id "+
				"WHERE diseaseinfo.disease=?";
		List<DiseaseInfo> list = QueryHelper.query(DiseaseInfo.class, sql, id);
		return list;
		
	}
	
	public static void main(String[] args) {
		
	}

	
}