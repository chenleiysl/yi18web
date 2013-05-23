package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.app.entity.Medicine;
import cn.yi18.entity.DrugClass;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.POJO;

public class DrugClassDao 

{

	/**
	 * 取得药品分类，和每个药品分类的药品总数
	 * @return
	 */
	public List<DrugClass> getDrugClass()
	{
		List<DrugClass> list = new ArrayList<DrugClass>();
		Drugclass bean = new Drugclass();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", 1);
		List<Drugclass> Drugclasses = (List<Drugclass>) bean.getlist(map );
		for (Drugclass drugclass : Drugclasses) 
		{
			
			
			DrugClass drugClass = new DrugClass();
			drugClass.setDrugclass(drugclass);
//			map.clear();
//			map.put("_parentId", drugclass.getId());
			String sql = "SELECT drugclass.id,drugclass.title,drug.level FROM " +
					"yi18_drugclass drugclass," +
						" (SELECT drug.drugclass,count(*) as level " +
						" FROM yi18_drug drug " +
						" WHERE drug.allow=1 " +
						" GROUP BY drug.drugclass) drug " +
					" WHERE drugclass.level=2 AND drugclass.id=drug.drugclass AND drugclass._parentId=? " +
					" ORDER BY drug.level";
			
			drugClass.setList(QueryHelper.query(Drugclass.class, sql,drugclass.getId()));
			
			list.add(drugClass);
		}
		
		
		return list;
		
	}
	
	
	
	public List<Medicine> getMedicineClass()
	{
		String sql ="select Drugclass.id,Drugclass.title,Drugclass.parent,Drug.count FROM  (SELECT D.id,D.title,D.level ,A.title parent FROM  yi18_drugclass D "+
					" LEFT OUTER JOIN yi18_drugclass A  "+
					" ON D._parentId = A.id "+
					" WHERE D.level=2) as Drugclass , "+
					" (SELECT drug.drugclass id,count(*) as count  "+
					" FROM yi18_drug drug  "+
					" WHERE drug.allow=1  "+
					" GROUP BY drug.drugclass) as Drug "+
					" WHERE Drugclass.id=Drug.id";
		
		return QueryHelper.query(Medicine.class, sql);
	}
	
}
