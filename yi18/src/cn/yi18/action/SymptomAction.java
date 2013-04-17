package cn.yi18.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.time.DateFormatUtils;

import cn.yi18.entity.DrugClass;
import cn.yi18.entity.DrugInfo;
import cn.yi18.entity.SymptomClass;
import cn.yi18.entity.SymptomInfo;
import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.Factory;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Symptomclass;
import cn.yi18.pojo.Symptominfo;
import cn.yi18.pojo.Symptoms;
import cn.yi18.service.DirectoryService;
import cn.yi18.service.DrugClassService;
import cn.yi18.service.DrugInfoService;
import cn.yi18.service.DrugService;
import cn.yi18.service.FactoryService;
import cn.yi18.service.SymptomClassService;
import cn.yi18.service.SymptomInfoService;
import cn.yi18.service.SymptomService;
import cn.yi18.util.PageUtil;

public class SymptomAction extends BaseAction
{
	private final int SIZE=10;
	
	
	
	
	/**
	 * 添加病状信息
	 * @throws FileUploadException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void add() throws FileUploadException, IllegalAccessException, InvocationTargetException
	{
		if(request.isSubmit())
		{
		
			Map<String, String[]> map = request.getParameterMap();
			Symptoms bean = new Symptoms();
			BeanUtils.populate(bean, map);
			long id=bean.save();
			
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
			    String name= names.nextElement();
			    
			    if(name.startsWith("editor"))
				   {
					   Symptominfo symptominfo = new Symptominfo();
					   symptominfo.setSymptoms(id);
					   symptominfo.setMessage(request.getParameter(name));   
					   symptominfo.setDirectory(Long.parseLong(name.split("_")[1]));
					   symptominfo.save();
					
				   }
			  
			}
		
			
			root.put("message", "添加成功！等待审核！");
			printFreemarker("default/message.ftl", root);
			
		}else {
			
			List<Directory> list = directoryService.getSymptom();
			root.put("list", list);
			
			printFreemarker("default/add_symptom.ftl", root);
			
		}
	}
	
	
	/**
	 * 更新病状信息
	 * @throws FileUploadException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void update() throws FileUploadException, IllegalAccessException, InvocationTargetException
	{
		
		Map<String, String[]> map = request.getParameterMap();
		Symptoms bean = new Symptoms();
		BeanUtils.populate(bean, map);
		
		Enumeration<String> names = request.getParameterNames();
		List<Symptominfo> list = new ArrayList<Symptominfo>();
		while (names.hasMoreElements()) {
		    String name= names.nextElement();
		    
		    if(name.startsWith("editor"))
			   {
				   Symptominfo symptominfo = new Symptominfo();
				   //symptominfo.setSymptoms(bean.getId());
				   symptominfo.setMessage(request.getParameter(name));   
				   symptominfo.setId(Long.parseLong(name.split("_")[1]));
				   list.add(symptominfo);
				  // symptominfo.save();
				   
				
			   }
		  
		}
		symptomService.upadte(bean, list);
		sendRedirect(request.basePath()+"admin/symptom");
		return;
			
	}
	
	
	
	/**
	 * 显示病状信息
	 */
	public void show()
	{
		String[] params = request.getParams();
		if(params!=null)
		{
			Long id = Long.parseLong(params[0]);
			Symptoms bean = new Symptoms();
			Symptoms symptoms = bean.get(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("count", symptoms.getCount()+1);
			bean.update(map , id);
			List<SymptomInfo> list = symptomInfoService.getSymptomInfo(id);
			root.put("symptoms", symptoms);
			root.put("list", list);
			
			printFreemarker("default/symptom.ftl", root);
		}
	}

	
public void list() throws ServletException, IOException {
		
	
	 DrugService drugService = new DrugService();
	
	 
	
		int page= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		// 取得页面，如果没有默认为1
		List<SymptomClass> tree = symptomClassService.getTree();
		root.put("tree", tree);
		
		String[] params = request.getParams();
		if(params==null)
		{
			List<Symptoms> news = symptomService.getNew(SIZE);
			PageUtil hots = symptomService.getPageHot(page, SIZE);
			root.put("id", 0);
			root.put("news", news);
			root.put("page", hots);
			root.put("open", tree.get(0).getSymptomclass().getId());
			
		}else 
		{
			long id = Long.parseLong(params[0]);//药品分类的id
			Symptomclass bean = new Symptomclass();
			Symptomclass symptomclass = bean.get(id); //取得分类
			
			
			List<Symptoms> news = symptomService.getNew(SIZE,id);
			PageUtil hots = symptomService.getPageHot(page, SIZE,id);
			root.put("id", id);
			root.put("news", news);
			root.put("page", hots);
			root.put("symptomclass",symptomclass);
			root.put("open", symptomclass.get_parentId());//打开的栏目
		}
		
		
		//root.put("open", 1);
		printFreemarker("default/symptom_list.ftl", root);
	}
	
	
	private DirectoryService directoryService = new DirectoryService();
	private SymptomInfoService symptomInfoService = new SymptomInfoService();
	private SymptomService  symptomService = new SymptomService();
	private SymptomClassService symptomClassService = new SymptomClassService();
	
	
}
