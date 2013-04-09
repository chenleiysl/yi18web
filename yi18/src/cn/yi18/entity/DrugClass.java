package cn.yi18.entity;

import java.util.List;

import cn.yi18.pojo.Drugclass;

/**
 * 用于界面Tree
 * @author 陈磊
 *
 */
public class DrugClass {

	private Drugclass drugclass;
	private List<Drugclass> list;
	
	public Drugclass getDrugclass() {
		return drugclass;
	}
	public void setDrugclass(Drugclass drugclass) {
		this.drugclass = drugclass;
	}
	public List<Drugclass> getList() {
		return list;
	}
	public void setList(List<Drugclass> list) {
		this.list = list;
	}
	

}
