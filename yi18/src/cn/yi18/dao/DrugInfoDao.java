package cn.yi18.dao;

import java.util.List;

import cn.yi18.entity.DrugInfo;
import cn.yi18.jdbc.QueryHelper;

public class DrugInfoDao 
{
	public List<DrugInfo> getDrugInfo(long id)
	{
		
		String sql = "SELECT directory.title,druginfo.message,druginfo.id FROM "+
				"yi18_directory directory JOIN yi18_druginfo druginfo  "+   
				"ON druginfo.directory =directory.id "+
				"WHERE druginfo.drug=?";
		List<DrugInfo> list = QueryHelper.query(DrugInfo.class, sql, id);
		return list;
		
	}
	
	public static void main(String[] args) {
		DrugInfoDao dao = new DrugInfoDao();
		List<DrugInfo> list = dao.getDrugInfo(1);
		
		System.out.print("");
	}
}
