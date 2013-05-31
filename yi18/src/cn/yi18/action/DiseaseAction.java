package cn.yi18.action;


import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import java.util.Enumeration;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.cache.VisitLogEhCache;
import cn.yi18.entity.DiseaseClass;
import cn.yi18.entity.DiseaseInfo;

import cn.yi18.pojo.Departments;
import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Diseaseclass;
import cn.yi18.pojo.Diseaseinfo;

import cn.yi18.pojo.Place;

import cn.yi18.service.DirectoryService;
import cn.yi18.service.DiseaseClassService;
import cn.yi18.service.DiseaseInfoService;
import cn.yi18.service.DiseaseService;

import cn.yi18.util.Base64Coder;
import cn.yi18.util.PageUtil;

/**
 * 疾病的操作
 * @author 陈磊
 *
 */
public class DiseaseAction extends BaseAction
{
	private final int SIZE=10;
	
	private static final Logger log= LoggerFactory.getLogger(DiseaseAction.class);
	
	
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
		
			log.debug("添加疾病信息");
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
			Disease disease = new Disease();
			disease = disease.get(id);
			if(disease==null){
				run_404();
				return;
			}//如果不存在就返回404页面
			
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
		
		
		int page= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		// 取得页面，如果没有默认为1
		List<DiseaseClass> tree = diseaseClassService.getTree();
		root.put("tree", tree);
		
		Departments department = new Departments();
		@SuppressWarnings("unchecked")
		List<Departments> departments = (List<Departments>) department.list();
		root.put("departments", departments);
		
		Place place = new Place();
		@SuppressWarnings("unchecked")
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
			root.put("title","常见疾病|疾病信息_医药吧");
			root.put("name","常见疾病");
			
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
			root.put("title",diseaseclass.getTitle()+"|疾病信息_医药吧");
			root.put("name",diseaseclass.getTitle());
			//root.put("open", symptomclass.get_parentId());//打开的栏目
		}else if(params.length==2){
			if(params[1].equals("place"))
			{
				long id = Long.parseLong(params[0]);//药品分类的id
				List<Disease> news = diseaseService.getNPlace(1, 10, id);
				PageUtil hots = diseaseService.getPagePlace(page, SIZE,id);
				Place bean = new Place();
				bean=bean.get(id);
				root.put("title",bean.getName()+"|疾病信息_医药吧");
				root.put("name",bean.getName());
				root.put("news", news);
				root.put("page", hots);
			}else if(params[1].equals("departments"))
			{
				long id = Long.parseLong(params[0]);//药品分类的id
				List<Disease> news = diseaseService.getNDepartments(1, 10, id);
				PageUtil hots = diseaseService.getPageDepartments(page, SIZE,id);
				Departments bean = new Departments();
				bean=bean.get(id);
				root.put("title",bean.getName()+"|疾病信息_医药吧");
				root.put("name",bean.getName());
				root.put("news", news);
				root.put("page", hots);
			}
		}
		
		
		//root.put("open", 1);
		printFreemarker("default/disease_list.ftl", root);
	}
	
	
 	/**
		 * 删除综疾病信息
		 */
		public void delete()
		{
			long id= Long.parseLong(request.getParams()[0]);
			String url =Base64Coder.decodeBase64( request.getParameter("returnUrl")); //取得返回的URL
			Disease disease = new Disease();
			disease.delete(id);
			Diseaseinfo diseaseinfo = new Diseaseinfo();
			String filter = " disease="+id;
			diseaseinfo.delete(filter );
			
			EhCacheEngine.remove("Diseases");
			sendRedirect(url);
			
		}
	
	private DirectoryService directoryService = new DirectoryService();
	private DiseaseService diseaseService = new DiseaseService();
	private DiseaseInfoService diseaseInfoService = new DiseaseInfoService();
	private DiseaseClassService diseaseClassService = new DiseaseClassService();
}
