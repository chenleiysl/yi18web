package cn.yi18.lucene;

import java.util.List;



public class PageUtil 
{
	 private List<PageInfo> list;
	 private int page;
	 private int size;
	 private int totalpage;
	 private long total;
	 private int time;
	 public PageUtil() {
		// TODO Auto-generated constructor stub
	}
	 
	 public PageUtil(int page ,int size ,long total,int time,List<PageInfo> list) 
	 {
			this.page =page;
			this.size = size;
			this.total= total;
			this.totalpage = (int) (total%size==0?total/size:(total/size+1));
			this.time =time;
			this.list = list;
		}
	 
	 
	 
	public List<PageInfo> getList() {
		return list;
	}
	public void setList(List<PageInfo> list) {
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	 

}
