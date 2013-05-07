package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

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
		Drug bean = new Drug();
		String filter = " allow = "+DrugEnum.Check_Status.IsCheck.getValue();
		return (List<Drug>) bean.filter(filter , 1, size);
	}
	
	/**
	 * 取得访问最多的药品
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Drug> getHot(int page,int size) {
		return  drugDao.getHot(page, size);
	}

	public List<Drug> getNew(int size, long id) {
		
		Drug bean = new Drug();
		String filter = " allow = "+DrugEnum.Check_Status.IsCheck.getValue()+" AND drugclass="+id;
		return (List<Drug>) bean.filter(filter , 1, size);
	}

	public List<Drug> getHot(int page, int size, long id) 
	{
		
		return drugDao.getHot(page, size,id);
	}
	
	
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public PageUtil getPageHot(int page, int size) {
		Drug bean = new Drug();
		String filter =  " allow = "+DrugEnum.Check_Status.IsCheck.getValue();
		int total = bean.totalCount(filter);//取得总数
		List<Drug> list = getHot(page, size);
		PageUtil pageUtil = new PageUtil(list , page, size, total);
		return pageUtil;
		
	}
	
	public PageUtil getPageHot(int page, int size, long id) {
		Drug bean = new Drug();
		String filter = " allow = "+DrugEnum.Check_Status.IsCheck.getValue()+" AND drugclass="+id;
		int total = bean.totalCount(filter);//取得总数
		List<Drug> list = getHot(page, size,id);
		PageUtil pageUtil = new PageUtil(list , page, size, total);
		return pageUtil;
		
	}

}
