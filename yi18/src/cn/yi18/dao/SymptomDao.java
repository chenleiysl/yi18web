package cn.yi18.dao;

import java.util.List;

import cn.yi18.entity.DrugInfo;
import cn.yi18.entity.SymptomInfo;
import cn.yi18.enums.DrugEnum;
import cn.yi18.enums.SymptomEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Symptoms;

public class SymptomDao 
{
	
	
	
	/**
	 * 取得热门药品
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Symptoms> getHot(int page ,int size ) 
	{
		String sql ="SELECT * FROM yi18_symptoms WHERE allow = ?  ORDER BY count DESC";
		
		return QueryHelper.query_slice(Symptoms.class,sql, page,size, SymptomEnum.Check_Status.IsCheck.getValue());
	}

	public List<Symptoms> getHot(int page, int size, long id) {
			String sql ="SELECT * FROM yi18_symptoms WHERE allow = ? AND symptomsclass=? ORDER BY count DESC";
		
		return QueryHelper.query_slice(Symptoms.class,sql, page,size, SymptomEnum.Check_Status.IsCheck.getValue(),id);
	}
	
	
	public cn.yi18.app.entity.Symptom getSymptom(Long id)
	{
		cn.yi18.app.entity.Symptom symptom = new cn.yi18.app.entity.Symptom();
		String sql = "SELECT symptoms.id,symptoms.name,symptoms.count,symptoms.description message, "+
					"	symptomsclass.title symptomsclass "+
					"	FROM yi18_symptoms symptoms,yi18_symptomclass symptomsclass "+
					"	WHERE symptoms.symptomsclass = symptomsclass.id "+
					"	AND symptoms.allow=1 "+
					"	AND symptoms.id=?";
		symptom=QueryHelper.read(cn.yi18.app.entity.Symptom.class, sql, id);
		
		SymptomInfoDao symptomInfoDao = new SymptomInfoDao();
		List<SymptomInfo> list = symptomInfoDao.getSymptomInfo(id);
		String message="<h2>摘要信息</h2>"+symptom.getMessage();
		for (SymptomInfo symptomInfo : list)
		{
			
			message=message+"<div>"+symptomInfo.getMessage()+"</div>";
			
		}
		symptom.setMessage(message);
		return symptom;
		
	}
	

}
