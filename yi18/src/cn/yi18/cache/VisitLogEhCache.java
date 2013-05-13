package cn.yi18.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import net.sf.ehcache.Element;

import cn.yi18.entity.Visits;
import cn.yi18.enums.VisitEnum;
import cn.yi18.jdbc.QueryHelper;

/**
 * ，主要处理浏览器的相关信息
 * @author 陈磊
 *
 */
public class VisitLogEhCache 
{
	private static String fullyQualifiedName="Visit";

	/**
	 * 添加浏览量，++1
	 * @param id 是该信息的id
	 * @param type 主要是存放该信息的数据库表名
	 */
	public static void Add(long id,String type)
	{
		Serializable key = type+id;
		Visits visit=(Visits) EhCacheEngine.get(fullyQualifiedName, key);
		if(visit==null)
		{
			visit = new Visits();
			visit.setId(id);
			visit.setCount(1);
			visit.setType(type);
			EhCacheEngine.add(fullyQualifiedName, key, visit);
		}else {
			visit.setCount(visit.getCount()+1);
			EhCacheEngine.add(fullyQualifiedName, key, visit);
		}
		
	}
	public static void Add(long id,String type,int size)
	{
		Serializable key = type+id;
		Visits visit=(Visits) EhCacheEngine.get(fullyQualifiedName, key);
		if(visit==null)
		{
			visit = new Visits();
			visit.setId(id);
			visit.setCount(size);
			visit.setType(type);
			EhCacheEngine.add(fullyQualifiedName, key, visit);
		}else {
			visit.setCount(visit.getCount()+size);
			EhCacheEngine.add(fullyQualifiedName, key, visit);
		}
		
	}
	
	
	public static void Update()
	{
		
		//List<Object[]> list = new ArrayList<Object[]>();
		Map<String, List<Object[]>> map =new  HashMap<String, List<Object[]>>();
		
		Collection<Element> elements = EhCacheEngine.getValues(fullyQualifiedName);
		
		for (Element element : elements) {
			Visits visit=(Visits) element.getObjectValue();
			List<Object[]> list = map.get(visit.getType());
			if(list==null)
			{
				list = new ArrayList<Object[]>();
				Object[] e={visit.getCount(),visit.getId()};
				list.add(e);
				map.put(visit.getType(), list);
				
			}else {
				Object[] e={visit.getCount(),visit.getId()};
				list.add(e);
				map.put(visit.getType(), list);
				
			}
			
		}
		
		
		Set<String> keys = map.keySet();
		for (String string : keys) 
		{
			List<Object[]> list = map.get(string);
			_update(list, string);
		}
		
		EhCacheEngine.remove(fullyQualifiedName);//清除
		
		
	}
	
	
	private static void _update(List<Object[]> list,String Table)
	{
		String sql = "UPDATE "+Table+" SET count=count+? WHERE id=?";
		
		Object[] params = list.toArray() ;
		
		Object[][] arr=null;
		for (int i = 0; i < params.length; i++) {
			arr=ArrayUtils.add(arr, (Object[]) params[i]);
		}
		QueryHelper.batch(sql , arr);
		
	}
	
	public static void main(String[] args) {
		
	
		
	}
	
	
}
