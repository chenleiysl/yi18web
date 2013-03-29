package cn.yi18.service;

import java.util.List;

import cn.yi18.pojo.Directory;



public class DirectoryService 
{
	/**
	 * 取得全部的目录
	 * @return
	 */
	public List<Directory> getAll()
	{
		Directory bean = new Directory();
		return (List<Directory>) bean.list(); 
	}
}
