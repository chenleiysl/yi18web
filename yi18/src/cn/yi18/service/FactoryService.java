package cn.yi18.service;

import java.util.List;

import cn.yi18.pojo.Factory;

public class FactoryService 
{
	public List<Factory> getAll()
	{
		Factory bean =new  Factory();
		return (List<Factory>) bean.list();
		
	}
}
