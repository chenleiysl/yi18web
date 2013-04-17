package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.entity.SymptomClass;
import cn.yi18.pojo.Symptomclass;




public class SymptomClassDao 

{

	public List<SymptomClass> getSymptomClass()
	{
		List<SymptomClass> list = new ArrayList<SymptomClass>();
		Symptomclass bean = new Symptomclass();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", 1);
		List<Symptomclass> symptomclasses = (List<Symptomclass>) bean.getlist(map );
		for (Symptomclass symptomclass : symptomclasses) 
		{
			SymptomClass symptomClass = new SymptomClass();
			symptomClass.setSymptomclass(symptomclass);
			map.clear();
			map.put("_parentId", symptomclass.getId());
			symptomClass.setList((List<Symptomclass>) bean.getlist(map ));
			list.add(symptomClass);
		}
		
		
		return list;
		
	}
}
