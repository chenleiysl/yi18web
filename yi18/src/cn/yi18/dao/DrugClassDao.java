package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
