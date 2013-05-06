package cn.yi18.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class JsoupUtil 
{
	/**
	 * 把html过滤掉html，生成text
	 * @param html
	 * @return
	 */
	public static String Text(String html) {
		return Jsoup.clean(html, new Whitelist());//过滤html,生存TEXT
		
	}
	
	/**
	 * 取全部图片
	 * @return
	 */
	public static List<String> Images(String html) {
			
		List<String> list = new ArrayList<String>();
		//从词条中取得一张图片
				Document doc= Jsoup.parse(html);
				Elements imgs = doc.select("img");
				for(int i=0;i<imgs.size();i++){
					  Element img = imgs.get(i);
						String src = img.attr("src");
						
						//File file = new File(request.getSession().getServletContext().getRealPath("../"));
						list.add(src);//	
				}
		return list;
		
	}
	/**
	 * 取得第一张图片
	 * @param html
	 * @return
	 */
	public static String Image(String html) {
		
		//从词条中取得一张图片
				Document doc= Jsoup.parse(html);
				Elements imgs = doc.select("img");
				for(int i=0;i<imgs.size();i++){
					  Element img = imgs.get(i);
						String src = img.attr("src");
						
						//File file = new File(request.getSession().getServletContext().getRealPath("../"));
						return src;//
				}
				return null;
		
		
	}
	
}
