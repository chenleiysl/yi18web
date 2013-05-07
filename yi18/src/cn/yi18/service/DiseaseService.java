package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.dao.DiseaseDao;
import cn.yi18.dao.DrugDao;
import cn.yi18.dao.SymptomDao;
import cn.yi18.enums.DiseaseEnum;
import cn.yi18.enums.DrugEnum;
import cn.yi18.enums.DiseaseEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.lucene.DiseaseLucene;
import cn.yi18.lucene.DrugLucene;
import cn.yi18.lucene.IndexFiles;
import cn.yi18.lucene.PageInfo;
import cn.yi18.pojo.Departments;
import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Diseasedepartments;
import cn.yi18.pojo.Diseaseinfo;
import cn.yi18.pojo.Diseaseplace;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Place;
import cn.yi18.pojo.Symptominfo;
import cn.yi18.pojo.Disease;
import cn.yi18.util.JsoupUtil;
import cn.yi18.util.PageUtil;

/**
 * 
 * @author 陈磊
 *
 */
public class DiseaseService 
{
	
	
	public List<Disease> getNoCheck()
	{
		Disease bean = new Disease();
		String filter ="allow ="+ DiseaseEnum.Check_Status.NoCheck.getValue();
		return (List<Disease>) bean.filter(filter, 1, 20);
		
	}
	
	public void upadte(Disease Disease ,List<Diseaseinfo> list,String[] places,
			String[] departments)
	{
		
		String content="";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allow", Disease.getAllow());
		map.put("name", Disease.getName());
		map.put("description", Disease.getDescription());
		map.put("diseaseclass", Disease.getDiseaseclass());
		Disease.update(map , Disease.getId());
		content=content+Disease.getDescription();
		for (Diseaseinfo diseaseinfo : list) {
			map.clear();
			map.put("message", diseaseinfo.getMessage());
			diseaseinfo.update(map, diseaseinfo.getId());
			content=content+JsoupUtil.Text(diseaseinfo.getMessage());
		}
		/**
		 * 保存科室对应的疾病
		 */
		for (String id : departments) {
			Diseasedepartments diseasedepartments = new Diseasedepartments();
			diseasedepartments.setDisease(Disease.getId());
			diseasedepartments.setDepartments(Long.parseLong(id));
			diseasedepartments.save();
		}
		
		/**
		 * 保存身体部位对应的疾病
		 */
		for (String id : places) {
			Diseaseplace diseaseplace = new Diseaseplace();
			diseaseplace.setDisease(Disease.getId());
			diseaseplace.setPlace(Long.parseLong(id));
			diseaseplace.save();
		}
		IndexFiles indexFiles = new DiseaseLucene();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTitle( Disease.getName());
		pageInfo.setId(Disease.getId());
		pageInfo.setUrl("disease/show/"+Disease.getId());
		
		pageInfo.setContent(content);
		indexFiles.create(pageInfo );
	}

	public List<Disease> getNew(int size) 
	{
		Disease bean = new Disease();
		String filter = " allow =  "+DiseaseEnum.Check_Status.IsCheck.getValue();
		return (List<Disease>) bean.filter(filter , 1, size);
	}

	
	public List<Disease> getHot(int page,int size) 
	{
		DiseaseDao diseaseDao = new DiseaseDao();
		return  diseaseDao.getHot(page, size);
	}
	
	public List<Disease> getHot(int page,int size,long id) 
	{
		DiseaseDao diseaseDao = new DiseaseDao();
		return  diseaseDao.getHot(page, size,id);
	}
	
	public PageUtil getPageHot(int page, int size) 
	{
		Disease bean = new Disease();
		String filter =  " allow = "+DiseaseEnum.Check_Status.IsCheck.getValue();
		int total = bean.totalCount(filter);//取得总数
		return new PageUtil(getHot(page, size), page, size, total);
	}

	public List<Disease> getNew(int size, long id) {
		
		Disease bean = new Disease();
		String filter = " allow =  "+DiseaseEnum.Check_Status.IsCheck.getValue() +" AND diseaseclass="+id;
		return (List<Disease>) bean.filter(filter , 1, size);
	}

	public PageUtil getPageHot(int page, int size, long id) {
		Disease bean = new Disease();
		String filter =  " allow = "+DiseaseEnum.Check_Status.IsCheck.getValue()+" AND diseaseclass="+id;
		int total = bean.totalCount(filter);//取得总数
		return new PageUtil(getHot(page, size,id), page, size, total);
	}

	
	public List<Departments> getDepartments(long id) {
		return diseaseDao.getDepartments(id);
	}
	public List<Place> getPlace(long id) {
		return diseaseDao.getPlace(id);
	}
	
	private DiseaseDao diseaseDao = new DiseaseDao();
	

	
}
