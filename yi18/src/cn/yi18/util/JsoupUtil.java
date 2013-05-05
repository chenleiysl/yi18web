package cn.yi18.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class JsoupUtil 
{
	/**
	 * 把html过滤掉html，生成text
	 * @param html
	 * @return
	 */
	public static String Text(String html) {
		return Jsoup.clean(html, Whitelist.simpleText());//过滤html
		
	}
}
