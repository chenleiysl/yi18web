package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.app.entity.Medicine;
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
	
	
	
	public List<Medicine> getMedicineClass()
	{
		String sql ="select Symptomclass.id,Symptomclass.title,Symptomclass.parent,Symptom.count FROM  "+
					" (SELECT D.id,D.title,D.level ,A.title parent FROM  yi18_symptomclass D  "+
					" LEFT OUTER JOIN yi18_symptomclass A  "+
					" ON D._parentId = A.id "+
					" WHERE D.level=2) as Symptomclass , "+
					" (SELECT symptoms.symptomsclass id,count(*) as count "+ 
					" FROM yi18_symptoms symptoms "+ 
					" WHERE symptoms.allow=1 "+ 
					" GROUP BY symptoms.symptomsclass) as Symptom "+
					" WHERE Symptomclass.id=Symptom.id";
		
		return QueryHelper.query(Medicine.class, sql);
	}
}
