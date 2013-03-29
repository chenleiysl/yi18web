package cn.yi18.service;

import java.util.List;

import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;

public class DrugService 
{
	public void save(Drug drug,List<Druginfo> druginfos)
	{
		long id = drug.save();
		for (Druginfo druginfo : druginfos) {
			druginfo.setDrug(id);
			druginfo.save();
		}
	}

}
