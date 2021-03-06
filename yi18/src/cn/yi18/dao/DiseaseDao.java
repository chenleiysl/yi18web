package cn.yi18.dao;

import java.util.List;

import cn.yi18.entity.DiseaseInfo;
import cn.yi18.entity.DrugInfo;
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
	 * 取得科室
	 * @param id
	 * @return
	 */
	public List<Departments> getDepartments(long id) {
		String sql ="SELECT departments.id,departments.name FROM "+
				" yi18_departments departments,yi18_diseasedepartments diseasedepartments "+
				" WHERE departments.id =diseasedepartments.departments AND diseasedepartments.disease=?";	
		return QueryHelper.query(Departments.class, sql, id);
		
	}
	/**
	 * 取得身体部位
	 * @param id
	 * @return
	 */
	public List<Place> getPlace(long id) {
		String sql ="SELECT place.id,place.name "+
				" FROM yi18_diseaseplace diseaseplace,yi18_place place"+
				" WHERE diseaseplace.place = place.id AND diseaseplace.disease=?";	
		return QueryHelper.query(Place.class, sql, id);
		
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @return
	 */
	public List<Disease> getHDepartments(int page, int size, long id) {
		String sql ="SELECT disease.id,disease.name,disease.description,disease.diseaseclass,disease.infectious,disease.count,disease.allow,disease.time "+
				" FROM yi18_disease disease,yi18_diseasedepartments diseasedepartments "+
				" WHERE disease.id=diseasedepartments.disease AND diseasedepartments.departments=? ORDER BY disease.count DESC";
	
		return QueryHelper.query_slice(Disease.class,sql, page,size,id);
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @return
	 */
	public List<Disease> getNDepartments(int page, int size, long id) {
		String sql ="SELECT disease.id,disease.name,disease.description,disease.diseaseclass,disease.infectious,disease.count,disease.allow,disease.time "+
				" FROM yi18_disease disease,yi18_diseasedepartments diseasedepartments "+
				" WHERE disease.id=diseasedepartments.disease AND diseasedepartments.departments=? ORDER BY disease.id DESC";
			return QueryHelper.query_slice(Disease.class,sql, page,size,id);
	}
	public long getDepartmentsCount(long id) {
		String sql ="SELECT COUNT(*) "+
				" FROM yi18_disease disease,yi18_diseasedepartments diseasedepartments "+
				" WHERE disease.id=diseasedepartments.disease AND diseasedepartments.departments=? ";
			return QueryHelper.stat(sql, id);
	}
	
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @return
	 */
	public List<Disease> getHPlace(int page, int size, long id) {
		String sql ="SELECT disease.id,disease.name,disease.description,disease.diseaseclass,disease.infectious,disease.count,disease.allow,disease.time "+
				" FROM yi18_disease disease,yi18_diseaseplace diseaseplace "+
				" WHERE disease.id=diseaseplace.disease AND diseaseplace.place=? ORDER BY disease.count DESC";
	
		return QueryHelper.query_slice(Disease.class,sql, page,size,id);
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @return
	 */
	public List<Disease> getNPlace(int page, int size, long id) {
		String sql ="SELECT disease.id,disease.name,disease.description,disease.diseaseclass,disease.infectious,disease.count,disease.allow,disease.time "+
				" FROM yi18_disease disease,yi18_diseaseplace diseaseplace "+
				" WHERE disease.id=diseaseplace.disease AND diseaseplace.place=? ORDER BY disease.id DESC";
		return QueryHelper.query_slice(Disease.class,sql, page,size,id);
	}
	public long getPlaceCount(long id) {
		String sql ="SELECT COUNT(*) "+
				" FROM yi18_disease disease,yi18_diseaseplace diseaseplace "+
				" WHERE disease.id=diseaseplace.disease AND diseaseplace.place=?";
		return QueryHelper.stat(sql, id);
	}
	
	public static void main(String[] args) {
		DiseaseDao dao = new DiseaseDao();
		System.out.println(dao.getNPlace(1,4,3).size());
	}
	
	
	
	public cn.yi18.app.entity.Disease getDisease(Long id)
	{
		cn.yi18.app.entity.Disease disease = new cn.yi18.app.entity.Disease();
		String sql = "SELECT disease.id,disease.name,disease.count,disease.description message, "+
					"	CASE disease.infectious WHEN 0 THEN '不传染' WHEN 1 THEN '传染'  ELSE '其他' END infectious , "+
					"	diseaseclass.title diseaseclass "+
					"	FROM yi18_disease disease,yi18_diseaseclass diseaseclass "+
					"	WHERE disease.diseaseclass = diseaseclass.id  "+
					"	AND disease.allow=1 "+
					"	AND disease.id=?";
		disease=QueryHelper.read(cn.yi18.app.entity.Disease.class, sql, id);
		
		DiseaseInfoDao diseaseInfoDao = new DiseaseInfoDao(); 
		List<DiseaseInfo> list = diseaseInfoDao.getDiseaseinfo(id);
		
		String message="<h2>摘要信息</h2>"+disease.getMessage();
		for (DiseaseInfo diseaseInfo : list)
		{
			message=message+diseaseInfo.getMessage()+"</div>";
		}
		disease.setMessage(message);
		return disease;
		
	}

}
