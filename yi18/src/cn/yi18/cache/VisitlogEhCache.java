package cn.yi18.cache;

import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;

import net.sf.ehcache.Element;

public class VisitlogEhCache 
{

	private static String fullyQualifiedName ="visitlog";
	
	
	/**
	 * @param key 主要有touser和session组成
	 * @param objects @param params: touser,fromuser ,time
	 */
	public static void setlog(String key,Object[] objects)
	{
		
		EhCacheEngine.add(fullyQualifiedName , key, objects);
		
	}
	
	public static Object[][] getlog()
	{
		
		Collection<Element> list=EhCacheEngine.getValues(fullyQualifiedName);
		Object[][] obct = new Object[list.size()][3];
		int i=0;
		for (Element element : list) {
			Object[] ob =(Object[]) element.getObjectValue();
			obct[i]=ob;
			i++;
		}
		return obct.length>0?obct:null;
				
	}
	
	
	public static void remove() 
	{
		EhCacheEngine.remove(fullyQualifiedName);
	}
	
	public static void main(String[] args) {
		Object[] objects = {1,2,3};
		for (int i = 0; i < 10; i++) {
			setlog(i+"", objects);
		}
		
		 Object[][] ob = getlog();
	
		System.out.println(ob[1][0]+""+ob[1][2]+ob[1][1]);
	
		remove();
		 ob = getlog();
		 System.out.println(ob);
	}
}
