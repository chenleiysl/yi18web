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

import cn.yi18.entity.SymptomClass;
import cn.yi18.entity.SymptomInfo;
import cn.yi18.pojo.Directory;

import cn.yi18.pojo.Symptomclass;
import cn.yi18.pojo.Symptominfo;
import cn.yi18.pojo.Symptoms;
import cn.yi18.service.DirectoryService;

import cn.yi18.service.SymptomClassService;
import cn.yi18.service.SymptomInfoService;
import cn.yi18.service.SymptomService;
import cn.yi18.util.Base64Coder;
import cn.yi18.util.PageUtil;

public class SymptomAction extends BaseAction
{
	private final int SIZE=10;
	
	private static final Logger log= LoggerFactory.getLogger(SymptomAction.class);

	
	
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
		
			log.debug("添加病状");
			root.put("message", "添加成功！等待审核！");
			printFreemarker("default/message.ftl", root);
			
		}else {
			
			List<Directory> list = directoryService.getSymptom();
			root.put("list", list);
			root.put("title", "新增病状信息 |病状信息_医药吧 ");
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
			if(symptoms==null){
				run_404();//如果不存在就返回404页面
				return;
			}
			
			
			
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("count", symptoms.getCount()+1);
//			bean.update(map , id);
			VisitLogEhCache.Add(symptoms.getId(), "yi18_symptoms");//更新阅读数
			List<SymptomInfo> list = symptomInfoService.getSymptomInfo(id);
			Symptomclass symptomclass = new Symptomclass();
			symptomclass=symptomclass.get(symptoms.getSymptomsclass());
			root.put("symptomclass", symptomclass);
			root.put("symptoms", symptoms);
			root.put("list", list);
			root.put("title", symptoms.getName()+"|病状信息_医药吧");
			root.put("keywords", symptoms.getName());
			root.put("description",symptoms.subDescription(100));
			printFreemarker("default/symptom.ftl", root);
		}
	}

	
	/**
	 * 显示病状列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list() throws ServletException, IOException {
		
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
			root.put("title", "病状知识|病状信息_医药吧");
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
			root.put("title", symptomclass.getTitle()+"|病状信息_医药吧");
			root.put("keywords", symptomclass.getTitle());
			root.put("description",symptomclass.getTitle());
		}
		
		
		//root.put("open", 1);
		printFreemarker("default/symptom_list.ftl", root);
	}
	
	
	/**
	 * 删除综病状信息
	 */
	public void delete()
	{
		long id= Long.parseLong(request.getParams()[0]);
		String url =Base64Coder.decodeBase64( request.getParameter("returnUrl")); //取得返回的URL
		Symptoms symptoms = new Symptoms();
		symptoms.delete(id);
		Symptominfo symptominfo = new Symptominfo();
		String filter = " symptoms="+id;
		symptominfo.delete(filter );
		
		EhCacheEngine.remove("Symptomses");
		sendRedirect(url);
	}
	
	private DirectoryService directoryService = new DirectoryService();
	private SymptomInfoService symptomInfoService = new SymptomInfoService();
	private SymptomService  symptomService = new SymptomService();
	private SymptomClassService symptomClassService = new SymptomClassService();
	
	
}
