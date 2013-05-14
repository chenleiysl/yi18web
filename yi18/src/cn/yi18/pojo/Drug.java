package cn.yi18.pojo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * 药品信息
 * @author 陈磊
 *
 */
public class Drug extends POJO 
{
	protected String name ;//名称
	protected String alias ;//别名
	protected String term;//药品词条，基本信息
	protected String image ;// 图片
	protected int prescription;//药品的处方类型，1：处方药，0：非处方的药
	protected int ingredient;//药品的组成成分，0：中药，1：中成药：2西药
	protected float price;//产考价格
	protected Long factory;//生产厂家
	protected int allow;//是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过
	protected Long drugclass;//药品分类
	protected int count;//访问次数
	protected Timestamp time = new Timestamp(new Date().getTime());//时间
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrescription() {
		return prescription;
	}
	public void setPrescription(int prescription) {
		this.prescription = prescription;
	}
	public int getIngredient() {
		return ingredient;
	}
	public void setIngredient(int ingredient) {
		this.ingredient = ingredient;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Long getFactory() {
		return factory;
	}
	public void setFactory(Long factory) {
		this.factory = factory;
	}
	public int getAllow() {
		return allow;
	}
	public void setAllow(int allow) {
		this.allow = allow;
	}
	public Long getDrugclass() {
		return drugclass;
	}
	public void setDrugclass(Long drugclass) {
		this.drugclass = drugclass;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	

	public String subTerm(int size)
	{
		//首先删除内容里面的图片
//		Document doc= Jsoup.parse(term);
//		Elements imgs = doc.select("img");
//		for (Element img : imgs) {
//			img.remove();
//		}
//		
		//String text= Jsoup.clean(term, Whitelist.simpleText());//过滤所有的html标签
		
		String r=StringUtils.substring(term, 0, size);
		if (term.length()>size) 
		{
			r=r+"…";
		}
		return r;
	}
	
	public String subTermHtml(int size)
	{
		//首先删除内容里面的图片
//		Document doc= Jsoup.parse(term);
//		Elements imgs = doc.select("img");
//		for (Element img : imgs) {
//			img.remove();
//		}
//		
		String text= Jsoup.clean(term, Whitelist.simpleText());//过滤所有的html标签
		
		String r=StringUtils.substring(text, 0, size);
		if (term.length()>size) 
		{
			r=r+"…";
		}
		return r;
	}
	
	
	/**
	 * 药品类型转换
	 * @return
	 */
	public String Ingredient()
	{
		String r;
		switch (ingredient) {
		case 0:
			r="中药";
			break;
		case 1:
			r="中成药";
			break;
		case 2:
			r="西药";
			break;
		default:
			r="其他";
			break;
		}
		
		return r;
	}
	
	/**
	 * 处方类型
	 * @return
	 */
	public String Prescription()
	{
		String r;
		switch (prescription) {
		case 1:
			r="处方药";
			break;
		case 0:
			r="非处方的药";
			break;
		
		default:
			r="其他";
			break;
		}
		
		return r;
	}
	

	@Override
	protected boolean isObjectCachedByID() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

}
