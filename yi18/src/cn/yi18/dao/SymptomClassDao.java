package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.entity.SymptomClass;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Drugclass;
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
			//map.clear();
			//map.put("_parentId", symptomclass.getId());
			//symptomClass.setList((List<Symptomclass>) bean.getlist(map ));
			String sql = "SELECT symptomclass.id,symptomclass.title,symptom.level FROM "+
					" yi18_symptomclass symptomclass, "+
					"	 (SELECT symptoms.symptomsclass,count(*) as level "+
					"	 FROM yi18_symptoms symptoms "+
					"	 WHERE symptoms.allow=1 "+
					"	 GROUP BY symptoms.symptomsclass) symptom "+
					"  WHERE symptomclass.level=2 AND symptomclass.id=symptom.symptomsclass "+
					" AND symptomclass._parentId=? "+
					"  ORDER BY symptom.level";
			
			symptomClass.setList(QueryHelper.query(Symptomclass.class, sql,symptomclass.getId()));
			
			list.add(symptomClass);
		}
		
		
		return list;
		
	}
}
