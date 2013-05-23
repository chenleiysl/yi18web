package cn.yi18.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.app.entity.Medicine;
import cn.yi18.entity.DiseaseClass;
import cn.yi18.jdbc.QueryHelper;
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
	
	
	public List<Medicine> getMedicineClass()
	{
		String sql ="select Diseaseclass.id,Diseaseclass.title,Diseaseclass.parent,Disease.count FROM  "+
					" (SELECT D.id,D.title,D.level ,A.title parent FROM  yi18_diseaseclass D  "+
					" LEFT OUTER JOIN yi18_diseaseclass A  "+
					" ON D._parentId = A.id "+
					" WHERE D.level=2) as Diseaseclass , "+
					" (SELECT disease.diseaseclass id,count(*) as count  "+
					" FROM yi18_disease disease  "+
					" WHERE disease.allow=1  "+
					" GROUP BY disease.diseaseclass) as Disease "+
					" WHERE Diseaseclass.id=Disease.id";
		
		return QueryHelper.query(Medicine.class, sql);
	}
}
