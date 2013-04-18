package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.enums.DirectoryEnum;
import cn.yi18.pojo.Directory;



public class DirectoryService 
{
	/**
	 * 取得全部的目录
	 * @return
	 */
	public List<Directory> getDrug()
	{
		Directory bean = new Directory();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", DirectoryEnum.Type.Drug.getValue());
		return (List<Directory>) bean.getlist(map ); 
	}
	
	/**
	 * 病状
	 * @return
	 */
	public List<Directory> getSymptom()
	{
		Directory bean = new Directory();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", DirectoryEnum.Type.Symptom.getValue());
		return (List<Directory>) bean.getlist(map ); 
	}
	
	public List<Directory> getDisease()
	{
		Directory bean = new Directory();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", DirectoryEnum.Type.Disease.getValue());
		return (List<Directory>) bean.getlist(map ); 
	}
}
