package cn.yi18.entity;

import java.io.Serializable;
import java.util.List;

import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Lore;
import cn.yi18.pojo.News;
import cn.yi18.pojo.Symptoms;

public class Index implements Serializable
{
	private List<News> news;//综合新闻
	private List<Lore> week;//本周健康知识
	private List<Lore> month;//本月健康知识热点
	private List<Disease> dnews ;//最新疾病
	private  List<Symptoms> snews;//最新病状
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
	public List<Lore> getWeek() {
		return week;
	}
	public void setWeek(List<Lore> week) {
		this.week = week;
	}
	public List<Lore> getMonth() {
		return month;
	}
	public void setMonth(List<Lore> month) {
		this.month = month;
	}
	public List<Disease> getDnews() {
		return dnews;
	}
	public void setDnews(List<Disease> dnews) {
		this.dnews = dnews;
	}
	public List<Symptoms> getSnews() {
		return snews;
	}
	public void setSnews(List<Symptoms> snews) {
		this.snews = snews;
	}
	
	
}
