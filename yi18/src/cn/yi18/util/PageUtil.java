package cn.yi18.util;

import java.util.List;

import cn.yi18.pojo.POJO;





public class PageUtil 
{
	 private List<? extends POJO> list;
	 private int page;
	 private int size;
	 private int totalpage;
	 public PageUtil(List<? extends POJO> list,int page, int size, int total) 
	 {
		this.list= list;
		this.page= page;
		this.size = size<1 ?Integer.MAX_VALUE:size;
		this.totalpage = total%size==0?total/size:(total/size+1);
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
	 

}
