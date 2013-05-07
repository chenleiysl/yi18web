package cn.yi18.dao;

import java.util.List;

import cn.yi18.enums.DiseaseEnum;
import cn.yi18.enums.DrugEnum;
import cn.yi18.enums.SymptomEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Departments;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Place;

public class DiseaseDao 
{
	
	
	
	/**
	 * 取得热门疾病
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Disease> getHot(int page ,int size ) 
	{
		String sql ="SELECT * FROM yi18_disease WHERE allow = ?  ORDER BY count DESC";
		
		return QueryHelper.query_slice(Disease.class,sql, page,size, DiseaseEnum.Check_Status.IsCheck.getValue());
	}

	public List<Disease> getHot(int page, int size, long id) {
			String sql ="SELECT * FROM yi18_disease WHERE allow = ? AND diseaseclass=? ORDER BY count DESC";
		
		return QueryHelper.query_slice(Disease.class,sql, page,size, DiseaseEnum.Check_Status.IsCheck.getValue(),id);
	}
	
	
	/**
	 * 取得身体部位
	 * @param id
	 * @return
	 */
	public List<Departments> getDepartments(long id) {
		String sql ="SELECT departments.id,departments.name FROM "+
				" yi18_departments departments,yi18_diseasedepartments diseasedepartments "+
				" WHERE departments.id =diseasedepartments.departments AND diseasedepartments.disease=?";	
		return QueryHelper.query(Departments.class, sql, id);
		
	}
	
	public List<Place> getPlace(long id) {
		String sql ="SELECT place.id,place.name "+
				" FROM yi18_diseaseplace diseaseplace,yi18_place place"+
				" WHERE diseaseplace.place = place.id AND diseaseplace.disease=?";	
		return QueryHelper.query(Place.class, sql, id);
		
	}
	

}
