package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.dao.DrugDao;
import cn.yi18.enums.DrugEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Symptominfo;
import cn.yi18.pojo.Symptoms;
import cn.yi18.util.PageUtil;

/**
 * 
 * @author 陈磊
 *
 */
public class SymptomService 
{
	
	
	public List<Symptoms> getNoCheck()
	{
		Symptoms bean = new Symptoms();
		String filter ="allow ="+DrugEnum.Check_Status.NoCheck.getValue();
		return (List<Symptoms>) bean.filter(filter, 1, 20);
		
	}
	
	public void upadte(Symptoms symptoms ,List<Symptominfo> list)
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allow", symptoms.getAllow());
		map.put("name", symptoms.getName());
		map.put("description", symptoms.getDescription());
		map.put("symptomsclass", symptoms.getSymptomsclass());
		symptoms.update(map , symptoms.getId());
		for (Symptominfo symptominfo : list) {
			map.clear();
			map.put("message", symptominfo.getMessage());
			symptominfo.update(map, symptominfo.getId());
		}
	}
	

}
