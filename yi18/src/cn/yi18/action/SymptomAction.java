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
	
	

	
	
	private DirectoryService directoryService = new DirectoryService();
	
	private SymptomService  symptomService = new SymptomService();
	
	
}
