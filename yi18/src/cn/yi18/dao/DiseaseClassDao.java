package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.entity.DiseaseClass;
import cn.yi18.pojo.Diseaseclass;




public class DiseaseClassDao 

{

	public List<DiseaseClass> getDiseaseClass()
	{
		List<DiseaseClass> list = new ArrayList<DiseaseClass>();
		Diseaseclass bean = new Diseaseclass();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", 1);
		List<Diseaseclass> Diseaseclasses = (List<Diseaseclass>) bean.getlist(map );
		for (Diseaseclass Diseaseclass : Diseaseclasses) 
		{
			DiseaseClass DiseaseClass = new DiseaseClass();
			DiseaseClass.setDiseaseclass(Diseaseclass);
			map.clear();
			map.put("_parentId", Diseaseclass.getId());
			DiseaseClass.setList((List<Diseaseclass>) bean.getlist(map ));
			list.add(DiseaseClass);
		}
		
		
		return list;
		
	}
}
