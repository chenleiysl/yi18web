package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.dao.DrugDao;
import cn.yi18.enums.DrugEnum;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;

public class DrugService 
{
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
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alias", drug.getAlias());
		map.put("name", drug.getName());
		map.put("image", drug.getImage());
		map.put("factory", drug.getFactory());
		map.put("ingredient", drug.getIngredient());
		map.put("term", drug.getTerm());
		map.put("price", drug.getPrice());
		map.put("drugclass", drug.getDrugclass());
		map.put("prescription", drug.getPrescription());
		map.put("allow", DrugEnum.Check_Status.IsCheck.getValue());
		drug.update(map , drug.getId());
		for (Druginfo druginfo : druginfos) {
			Map<String, Object> vmap = new HashMap<String, Object>();
			vmap.put("message", druginfo.getMessage());
			druginfo.update(vmap, druginfo.getId());
		}
		// TODO Auto-generated method stub
		
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

}
