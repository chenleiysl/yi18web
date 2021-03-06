package cn.yi18.util;

import java.io.Serializable;
import java.util.List;

import cn.yi18.pojo.POJO;





public class PageUtil implements Serializable
{
	 private List<? extends POJO> list;
	 private int page;
	 private int size;
	 private int totalpage;
	 private int total;
	 public PageUtil(List<? extends POJO> list,int page, int size, int total) 
	 {
		this.list= list;
		this.page= page;
		this.size = size<1 ?Integer.MAX_VALUE:size;
		this.totalpage = total%size==0?total/size:(total/size+1);
		this.total = total;
	}
	 public PageUtil() {
		
	}
	public List<? extends POJO> getList() {
		return list;
	}
	public void setList(List<? extends POJO> list) {
		this.list = list;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	 

}
