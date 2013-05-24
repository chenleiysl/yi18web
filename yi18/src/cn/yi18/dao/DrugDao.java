package cn.yi18.dao;

import java.util.List;

import cn.yi18.entity.DrugInfo;
import cn.yi18.enums.DrugEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;

public class DrugDao 
{
	public List<Drug> getNoCheck()
	{
		String sql ="SELECT * FROM yi18_drug WHERE allow = ? ORDER BY id ASC LIMIT 0,20";
		return QueryHelper.query(Drug.class, sql, DrugEnum.Check_Status.NoCheck.getValue());
		
	}
	
	
	/**
	 * 取得热门药品
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Drug> getHot(int page ,int size ) 
	{
		String sql ="SELECT * FROM yi18_drug WHERE allow = ?  ORDER BY count DESC";
		
		return QueryHelper.query_slice(Drug.class,sql, page,size, DrugEnum.Check_Status.IsCheck.getValue());
	}

	public List<Drug> getHot(int page, int size, long id) {
			String sql ="SELECT * FROM yi18_drug WHERE allow = ? AND drugclass=? ORDER BY count DESC";
		
		return QueryHelper.query_slice(Drug.class,sql, page,size, DrugEnum.Check_Status.IsCheck.getValue(),id);
	}
	
	
	public cn.yi18.app.entity.Drug getDrug(Long id)
	{
		cn.yi18.app.entity.Drug drug = new cn.yi18.app.entity.Drug();
		String sql = "SELECT drug.id,drug.name,drug.alias,drug.term,drug.image, "+
					"	CASE drug.prescription WHEN 0 THEN '处方药' WHEN 1 THEN '非处方的药'  ELSE '其他' END prescription , "+
					"	CASE drug.ingredient WHEN 0 THEN '中药' WHEN 1 THEN '中成药' WHEN 2 THEN '西药' ELSE '其他' END ingredient, "+
					"	drug.price,drug.count,drugclass.title drugclass,factory.name factory "+
					"	FROM yi18_drug drug,yi18_drugclass drugclass ,yi18_factory factory "+
					"	WHERE drug.drugclass = drugclass.id AND drug.factory=factory.id "+
					"	AND drug.allow=1 " +
					"	AND drug.id=?";
		drug=QueryHelper.read(cn.yi18.app.entity.Drug.class, sql, id);
		
		DrugInfoDao  drugInfoDao = new DrugInfoDao();
		List<DrugInfo> list = drugInfoDao.getDrugInfo(id);
		String message="<h2>摘要信息</h2>"+drug.getTerm();
		for (DrugInfo drugInfo : list)
		{
			
			message=message+"<h2>"+drugInfo.getTitle()+"</h2><div>"+drugInfo.getMessage()+"</div>";
			break;
		}
		drug.setMessage(message);
		return drug;
		
	}
	
	

}
