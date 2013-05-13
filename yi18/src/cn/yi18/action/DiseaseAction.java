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

import cn.yi18.cache.VisitLogEhCache;
import cn.yi18.entity.DiseaseClass;
import cn.yi18.entity.DiseaseInfo;
import cn.yi18.entity.DrugClass;
import cn.yi18.entity.DrugInfo;
import cn.yi18.entity.SymptomClass;
import cn.yi18.entity.SymptomInfo;
import cn.yi18.pojo.Departments;
import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Diseaseclass;
import cn.yi18.pojo.Diseaseinfo;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.Factory;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Place;
import cn.yi18.pojo.Symptomclass;
import cn.yi18.pojo.Symptominfo;
import cn.yi18.pojo.Symptoms;
import cn.yi18.service.DirectoryService;
import cn.yi18.service.DiseaseClassService;
import cn.yi18.service.DiseaseInfoService;
import cn.yi18.service.DiseaseService;
import cn.yi18.service.DrugClassService;
import cn.yi18.service.DrugInfoService;
import cn.yi18.service.DrugService;
import cn.yi18.service.FactoryService;
import cn.yi18.service.SymptomClassService;
import cn.yi18.service.SymptomInfoService;
import cn.yi18.service.SymptomService;
import cn.yi18.util.PageUtil;

public class DiseaseAction extends BaseAction
{
	private final int SIZE=10;
	
	
	
	
	/**
	 * 添加疾病信息
	 * @throws FileUploadException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void add() throws FileUploadException, IllegalAccessException, InvocationTargetException
	{
		if(request.isSubmit())
		{
		
			Map<String, String[]> map = request.getParameterMap();
			Disease bean = new Disease();
			BeanUtils.populate(bean, map);
			long id=bean.save();
			
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
			    String name= names.nextElement();
			    
			    if(name.startsWith("editor"))
				   {
					   Diseaseinfo diseaseinfo = new Diseaseinfo();
					   diseaseinfo.setDisease(id);
					   diseaseinfo.setMessage(request.getParameter(name));   
					   diseaseinfo.setDirectory(Long.parseLong(name.split("_")[1]));
					   diseaseinfo.save();
					
				   }
			  
			}
		
			
			root.put("message", "添加成功！等待审核！");
			
			printFreemarker("default/message.ftl", root);
			
		}else {
			
			List<Directory> list = directoryService.getDisease();
			root.put("list", list);
			root.put("title", "疾病发布|医药吧 ");
			printFreemarker("default/add_disease.ftl", root);
			
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
		
		String[] places=request.getParameterValues("places");//取得多个值
		String[] departments=request.getParameterValues("departments");
		Map<String, String[]> map = request.getParameterMap();
		Disease bean = new Disease();
		BeanUtils.populate(bean, map);
		
		Enumeration<String> names = request.getParameterNames();
		List<Diseaseinfo> list = new ArrayList<Diseaseinfo>();
		while (names.hasMoreElements()) {
		    String name= names.nextElement();
		    
		    if(name.startsWith("editor"))
			   {
				   Diseaseinfo diseaseinfo  = new Diseaseinfo();
				   //symptominfo.setSymptoms(bean.getId());
				   diseaseinfo.setMessage(request.getParameter(name));   
				   diseaseinfo.setId(Long.parseLong(name.split("_")[1]));
				   list.add(diseaseinfo);
				  // symptominfo.save();
				   
				
			   }
		  
		}
		diseaseService.upadte(bean, list,places,departments);
		sendRedirect(request.basePath()+"admin/disease");
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
			Disease bean = new Disease();
			Disease disease = bean.get(id);
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("count", disease.getCount()+1);
//			bean.update(map , id);
			
			VisitLogEhCache.Add(disease.getId(), "yi18_disease");//更新阅读数
			List<DiseaseInfo> list = diseaseInfoService.getDiseaseInfo(id);
			root.put("disease", disease);
			root.put("list", list);
			
			root.put("title", disease.getName()+"|疾病信息_医药吧");
			 root.put("keywords", disease.getName());
			 root.put("description", disease.subDescription(100));
			 
			 Diseaseclass diseaseclass = new Diseaseclass();
			 diseaseclass= diseaseclass.get(disease.getDiseaseclass());
			 root.put("diseaseclass", diseaseclass);
			 List<Departments> departments = diseaseService.getDepartments(disease.getId());
			 List<Place> places = diseaseService.getPlace(disease.getId());
			 root.put("departments", departments);
			
			 root.put("places", places);
			printFreemarker("default/disease.ftl", root);
		}
	}

	
	public void list() throws ServletException, IOException {
		
	
//	 DrugService drugService = new DrugService();
//	
//	 
//	
		int page= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		// 取得页面，如果没有默认为1
		List<DiseaseClass> tree = diseaseClassService.getTree();
		root.put("tree", tree);
		
		Departments department = new Departments();
		List<Departments> departments = (List<Departments>) department.list();
		root.put("departments", departments);
		
		Place place = new Place();
		List<Place> places = (List<Place>) place.list();
		root.put("places", places);
		
		
		String[] params = request.getParams();
		if(params==null)
		{
			List<Disease> news = diseaseService.getNew(SIZE);
			PageUtil hots = diseaseService.getPageHot(page, SIZE);
			//root.put("id", 0);
			root.put("news", news);
			root.put("page", hots);
			//root.put("open", tree.get(0).getSymptomclass().getId());
			root.put("title","常见疾病");
			
		}else if(params.length==1)
		{
			long id = Long.parseLong(params[0]);//药品分类的id
			Diseaseclass bean = new Diseaseclass();
			Diseaseclass diseaseclass = bean.get(id); //取得分类
			
			
			List<Disease> news = diseaseService.getNew(SIZE,id);
			PageUtil hots = diseaseService.getPageHot(page, SIZE,id);
			//root.put("id", id);
			root.put("news", news);
			root.put("page", hots);
			root.put("title",diseaseclass.getTitle());
			//root.put("open", symptomclass.get_parentId());//打开的栏目
		}else if(params.length==2){
			if(params[1].equals("place"))
			{
				long id = Long.parseLong(params[0]);//药品分类的id
				List<Disease> news = diseaseService.getNPlace(1, 10, id);
				PageUtil hots = diseaseService.getPagePlace(page, SIZE,id);
				Place bean = new Place();
				bean=bean.get(id);
				root.put("title",bean.getName());
				root.put("news", news);
				root.put("page", hots);
			}else if(params[1].equals("departments"))
			{
				long id = Long.parseLong(params[0]);//药品分类的id
				List<Disease> news = diseaseService.getNDepartments(1, 10, id);
				PageUtil hots = diseaseService.getPageDepartments(page, SIZE,id);
				Departments bean = new Departments();
				bean=bean.get(id);
				root.put("title",bean.getName());
				root.put("news", news);
				root.put("page", hots);
			}
		}
		
		
		//root.put("open", 1);
		printFreemarker("default/disease_list.ftl", root);
	}
	
	
	private DirectoryService directoryService = new DirectoryService();
	
	private DiseaseService diseaseService = new DiseaseService();
	private DiseaseInfoService diseaseInfoService = new DiseaseInfoService();
	private DiseaseClassService diseaseClassService = new DiseaseClassService();
}
