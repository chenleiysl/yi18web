package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

import cn.yi18.util.JsoupUtil;

/**
 * 健康知识
 * @author 陈磊
 *
 */
public class Lore extends POJO
{
	protected String title;//资讯标题
	protected String message;//资讯内容
	protected int count ;//访问次数
	protected int allow;//是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过
	protected String author;//作者
	protected Long loreclass;//健康知识
	protected Timestamp time = new Timestamp(new Date().getTime());//添加时间
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getLoreclass() {
		return loreclass;
	}
	public void setLoreclass(Long loreclass) {
		this.loreclass = loreclass;
	}
	public int getAllow() {
		return allow;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	public String subTitle(int size)
	{
//		String  content = JsoupUtil.Text(message);
		String r=StringUtils.substring(title, 0, size);
		if (title.length()>size) 
		{
			r=r+"…";
		}
		return r;
	}
	public String subMessage(int size)
	{
		String  content = JsoupUtil.Text(message);
		String r=StringUtils.substring(content, 0, size);
		if (content.length()>size) 
		{
			r=r+"…";
		}
		return r;
	}
	
	public String prettyTime() {
		PrettyTime p = new PrettyTime(new Locale("ZH_CN"));
        return p.format(new Date());
	}
	
	/**
	 * 取得内容中的图片
	 * @return
	 */
	public String Pic() 
	{
		String r="";
		List<String> list = JsoupUtil.Images(message);
		for (String string : list) {
			r=r+string+"||";
			//r=r+string+"||";
		}
		
		return StringUtils.removeEnd(r, "||");
		
	}
	
	@Override
	protected boolean isObjectCachedByID() {
		// TODO Auto-generated method stub
		return true;
	}
}
