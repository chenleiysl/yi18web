package cn.yi18.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.DrugDao;
import cn.yi18.enums.DrugEnum;
import cn.yi18.lucene.DrugLucene;
import cn.yi18.lucene.IndexFiles;
import cn.yi18.lucene.PageInfo;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.POJO;
import cn.yi18.util.JsoupUtil;
import cn.yi18.util.PageUtil;

/**
 * 药品处理的service层数据操作
 * @author 陈磊
 *
 */
public class DrugService 
{
	
	/**
	 * 保存药品信息
	 * @param drug 基本的药品信息
	 * @param druginfos 药品内容摘要信息
	 */
	public void save(Drug drug,List<Druginfo> druginfos)
	{
		long id = drug.save();
		for (Druginfo druginfo : druginfos) {
			druginfo.setDrug(id);
			druginfo.save();
		}
	}
	
	public List<Drug> getNoCheck()
	{
		return drugDao.getNoCheck();
		
	}
	
	private DrugDao drugDao = new DrugDao();

	public void update(Drug drug, List<Druginfo> druginfos)
	{
		
		
		String content = "";//搜索存放数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alias", drug.getAlias());
		map.put("name", drug.getName());
		map.put("image", drug.getImage());
		map.put("factory", drug.getFactory());
		map.put("ingredient", drug.getIngredient());
		map.put("term", JsoupUtil.Text(drug.getTerm()));//过滤html
		map.put("price", drug.getPrice());
		map.put("drugclass", drug.getDrugclass());
		map.put("prescription", drug.getPrescription());
		map.put("allow", DrugEnum.Check_Status.IsCheck.getValue());
		map.put("time", new Timestamp(new Date().getTime()));//同时也更新时间
		drug.update(map , drug.getId()); //更新药品基本信息
		
		content=content+JsoupUtil.Text(drug.getTerm());
		for (Druginfo druginfo : druginfos) {
			Map<String, Object> vmap = new HashMap<String, Object>();
			vmap.put("message", druginfo.getMessage());
			druginfo.update(vmap, druginfo.getId());
			
			content=content+JsoupUtil.Text(druginfo.getMessage());//
		}
		// TODO Auto-generated method stub
		
		IndexFiles indexFiles = new DrugLucene();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTitle( drug.getName());
		pageInfo.setId(drug.getId());
		pageInfo.setUrl("drug/show/"+drug.getId());
		pageInfo.setContent(content);
		indexFiles.create(pageInfo );
	}
	
	
	/**
	 * 取得最新的药品
	 * @param size
	 * @return
	 */
	public List<Drug> getNew(int size)
	{
		String fullyQualifiedName = "Drugs";
		Serializable key="new_"+size;
		List<Drug> list = (List<Drug>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			Drug bean = new Drug();
			String filter = " allow = "+DrugEnum.Check_Status.IsCheck.getValue();
			list= (List<Drug>) bean.filter(filter , 1, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		
		return list;
	}
	
	/**
	 * 取得访问最多的药品
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Drug> getHot(int page,int size) 
	{
		String fullyQualifiedName = "Drugs";
		Serializable key="hot_"+page+"and"+size;
		List<Drug> list = (List<Drug>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list= drugDao.getHot(page, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		
		return list;
		
	}

	public List<Drug> getNew(int size, long id) {
		
		String fullyQualifiedName = "Drugs";
		Serializable key="new_"+size+"and"+id;
		List<Drug> list = (List<Drug>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			Drug bean = new Drug();
			String filter = " allow = "+DrugEnum.Check_Status.IsCheck.getValue()+" AND drugclass="+id;
			
			list= (List<Drug>) bean.filter(filter , 1, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		
		return list;
		//return (List<Drug>) bean.filter(filter , 1, size);
	}

	public List<Drug> getHot(int page, int size, long id) 
	{
		
		String fullyQualifiedName = "Drugs";
		Serializable key="Hot_"+page+"and"+size+"and"+id;
		List<Drug> list = (List<Drug>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list= drugDao.getHot(page, size,id);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		
		return list;
		//return drugDao.getHot(page, size,id);
	}
	
	
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public PageUtil getPageHot(int page, int size) {
		
		String fullyQualifiedName = "Drugs";
		Serializable key="PageHot_"+page+"and"+size;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Drug bean = new Drug();
			String filter =  " allow = "+DrugEnum.Check_Status.IsCheck.getValue();
			int total = bean.totalCount(filter);//取得总数
			List<Drug> list = getHot(page, size);
			pageUtil = new PageUtil(list , page, size, total);
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
			 
		}
		return pageUtil;
		
	}
	
	public PageUtil getPageHot(int page, int size, long id) {
		
		String fullyQualifiedName = "Drugs";
		Serializable key="PageHot_"+page+"and"+size+"and"+id;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Drug bean = new Drug();
			String filter = " allow = "+DrugEnum.Check_Status.IsCheck.getValue()+" AND drugclass="+id;
			int total = bean.totalCount(filter);//取得总数
			List<Drug> list = getHot(page, size,id);
			pageUtil = new PageUtil(list , page, size, total);
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		return pageUtil;
		
	}

}
