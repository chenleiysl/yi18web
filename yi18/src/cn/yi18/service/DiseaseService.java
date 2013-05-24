package cn.yi18.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.cache.EhCacheEngine;
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
		map.put("time", new Timestamp(new Date().getTime()));//同时也更新时间
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
		String fullyQualifiedName = "Diseases";
		Serializable key="new_"+size;
		List<Disease> list = (List<Disease>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			Disease bean = new Disease();
			String filter = " allow =  "+DiseaseEnum.Check_Status.IsCheck.getValue();
			list= (List<Disease>) bean.filter(filter , 1, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
		
	}

	
	public List<Disease> getHot(int page,int size) 
	{
		String fullyQualifiedName = "Diseases";
		Serializable key="hot_"+page+"and"+size;
		List<Disease> list = (List<Disease>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			DiseaseDao diseaseDao = new DiseaseDao();
			list=  diseaseDao.getHot(page, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
		
	}
	
	public List<Disease> getHot(int page,int size,long id) 
	{
		String fullyQualifiedName = "Diseases";
		Serializable key="hot_"+page+"and"+size+"and"+id;
		List<Disease> list = (List<Disease>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			DiseaseDao diseaseDao = new DiseaseDao();
			list=  diseaseDao.getHot(page, size,id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}
	
	public PageUtil getPageHot(int page, int size) 
	{
		String fullyQualifiedName = "Diseases";
		Serializable key="pagehot_"+page+"and"+size;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Disease bean = new Disease();
			String filter =  " allow = "+DiseaseEnum.Check_Status.IsCheck.getValue();
			int total = bean.totalCount(filter);//取得总数
			pageUtil= new PageUtil(getHot(page, size), page, size, total);
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		
		return pageUtil;
	}

	public List<Disease> getNew(int size, long id) {
		
		String fullyQualifiedName = "Diseases";
		Serializable key="new_"+size+"and"+id;
		List<Disease> list = (List<Disease>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			Disease bean = new Disease();
			String filter = " allow =  "+DiseaseEnum.Check_Status.IsCheck.getValue() +" AND diseaseclass="+id;
			list= (List<Disease>) bean.filter(filter , 1, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}

	public PageUtil getPageHot(int page, int size, long id)
	{
		String fullyQualifiedName = "Diseases";
		Serializable key="pagehot_"+page+"and"+size+"and"+id;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Disease bean = new Disease();
			String filter =  " allow = "+DiseaseEnum.Check_Status.IsCheck.getValue()+" AND diseaseclass="+id;
			int total = bean.totalCount(filter);//取得总数
			pageUtil= new PageUtil(getHot(page, size,id), page, size, total);
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		
		return pageUtil;
	}

	
	public List<Departments> getDepartments(long id) 
	{
		String fullyQualifiedName = "Diseases";
		Serializable key="Departments_"+id;
		List<Departments> list = (List<Departments>) EhCacheEngine.get(fullyQualifiedName, key);
		if (list==null)
		{
			list=diseaseDao.getDepartments(id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}
	public List<Place> getPlace(long id) {
		String fullyQualifiedName = "Diseases";
		Serializable key="Place_"+id;
		List<Place> list = (List<Place>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list= diseaseDao.getPlace(id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
		
	}
	
	
	public PageUtil getPagePlace(int page, int size, long id) {
		
		String fullyQualifiedName = "Diseases";
		Serializable key="pageplace_"+page+"and"+size+"and"+id;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			int total = (int) diseaseDao.getPlaceCount(id);
			pageUtil= new PageUtil(diseaseDao.getHPlace(page, size,id), page, size, total );
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		
		return pageUtil;
	}

	public PageUtil getPageDepartments(int page, int size, long id) {
		
		String fullyQualifiedName = "Diseases";
		Serializable key="pageDepartments_"+page+"and"+size+"and"+id;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			int total = (int) diseaseDao.getDepartmentsCount(id);
			pageUtil= new PageUtil(diseaseDao.getHDepartments(page, size,id), page, size, total );
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		
		return pageUtil;
	}
	
	public List<Disease> getNPlace(int page, int size, long id) {
		
		String fullyQualifiedName = "Diseases";
		Serializable key="NPlace_"+page+"and"+size+"and"+id;
		List<Disease> list = (List<Disease>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list= diseaseDao.getNPlace( page, size, id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
		
	}
	
	public List<Disease> getNDepartments(int page, int size, long id) {
		String fullyQualifiedName = "Diseases";
		Serializable key="NDepartments_"+page+"and"+size+"and"+id;
		List<Disease> list = (List<Disease>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list= diseaseDao.getNDepartments(page, size, id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}
	
	public cn.yi18.app.entity.Disease getDisease( long id) 
	{
		return diseaseDao .getDisease(id);
	}
	
	
	private DiseaseDao diseaseDao = new DiseaseDao();
	

	
}
