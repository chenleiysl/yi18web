package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.entity.DrugClass;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.POJO;

public class DrugClassDao 

{

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
			map.clear();
			map.put("_parentId", drugclass.getId());
			drugClass.setList((List<Drugclass>) bean.getlist(map ));
			list.add(drugClass);
		}
		
		
		return list;
		
	}
}
