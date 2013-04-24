package cn.yi18.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;


import cn.yi18.entity.DiseaseInfo;
import cn.yi18.entity.DrugInfo;
import cn.yi18.entity.SymptomInfo;
import cn.yi18.enums.DirectoryEnum;
import cn.yi18.enums.NewsEnum;
import cn.yi18.pojo.Departments;
import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Disease;
import cn.yi18.pojo.Diseaseclass;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.Factory;
import cn.yi18.pojo.Links;
import cn.yi18.pojo.Lore;
import cn.yi18.pojo.Loreclass;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Partner;
import cn.yi18.pojo.Place;
import cn.yi18.pojo.Symptomclass;
import cn.yi18.pojo.Symptoms;
import cn.yi18.pojo.User;
import cn.yi18.service.DirectoryService;
import cn.yi18.service.DiseaseInfoService;
import cn.yi18.service.DiseaseService;
import cn.yi18.service.DrugInfoService;
import cn.yi18.service.DrugService;
import cn.yi18.service.FactoryService;
import cn.yi18.service.LinksService;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.service.PartnerService;
import cn.yi18.service.SymptomInfoService;
import cn.yi18.service.SymptomService;

public class AdminAction extends BaseAction {

	
	/**
	 * 默认admin页面
	 */
	@Override
	public void execute() throws ServletException, IOException {
		printFreemarker("admin/index.ftl", root);
	}
	
	
	/**
	 * 药品目录信息
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void directory() throws IllegalAccessException, InvocationTargetException {
		
		if(request.isSubmit())
		{
			Map map = request.getParameterMap();
			Directory directory = new Directory();
			BeanUtils.populate(directory , map);
			if(request.getParameter("sub").equals("save"))
			{
				
				  directory.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				
				  Directory bean = new Directory();
				  Map<String, Object> dmap = new HashMap<String, Object>();
				  dmap.put("title", directory.getTitle());
				  dmap.put("description", directory.getDescription());
				  dmap.put("issearch", directory.getIssearch());
				  dmap.put("sequence", directory.getSequence());
				 
				bean.update(dmap , directory.getId());
				 String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
			}
			  
			
		}else 
		{
			String type = request.getParams()[0];
			if(type.equals(DirectoryEnum.Type.Drug.getValue()+"")){
				List<Directory> list =directoryService.getDrug(); 
				root.put("list", list);
				printFreemarker("admin/directory_drug.ftl", root);
			}else if(type.equals(DirectoryEnum.Type.Symptom.getValue()+"")){
				List<Directory> list =directoryService.getSymptom(); 
				root.put("list", list);
				printFreemarker("admin/directory_symptom.ftl", root);
			}else if(type.equals(DirectoryEnum.Type.Disease.getValue()+"")){
				List<Directory> list =directoryService.getDisease(); 
				root.put("list", list);
				printFreemarker("admin/directory_disease.ftl", root);
			}
		}
		
	}
	
	
	public void place() throws IllegalAccessException, InvocationTargetException {
		
	
		if(request.isSubmit())
		{
			Map map = request.getParameterMap();
			Place place = new Place();
			BeanUtils.populate(place , map);
			if(request.getParameter("sub").equals("save"))
			{
				
				place.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				
				  
				  Map<String, Object> dmap = new HashMap<String, Object>();
				  dmap.put("name", place.getName());
				  
					Place bean = new Place();
				  bean.update(dmap , place.getId());
				  String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
			}
			  
			
		}else 
		{


			Place bean = new Place();
			List<Place> list = (List<Place>) bean.list();
			root.put("list", list);
			printFreemarker("admin/place.ftl", root);
			
		}
		
	}
	public void department() throws IllegalAccessException, InvocationTargetException {
		
		
		if(request.isSubmit())
		{
			Map map = request.getParameterMap();
			Departments departments = new Departments();
			BeanUtils.populate(departments , map);
			if(request.getParameter("sub").equals("save"))
			{
				
				departments.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				
				  
				  Map<String, Object> dmap = new HashMap<String, Object>();
				  dmap.put("name", departments.getName());
				  
				  Departments bean = new Departments();
				  bean.update(dmap , departments.getId());
				  String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
			}
			  
			
		}else 
		{


			 Departments bean = new Departments();
			List<Place> list = (List<Place>) bean.list();
			root.put("list", list);
			printFreemarker("admin/departments.ftl", root);
			
		}
		
	}
	
	
	public void loreclass() throws IllegalAccessException, InvocationTargetException {
		
		
		if(request.isSubmit())
		{
			Map map = request.getParameterMap();
			Loreclass loreclass = new Loreclass();
			BeanUtils.populate(loreclass , map);
			if(request.getParameter("sub").equals("save"))
			{
				
				loreclass.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				
				  
				  Map<String, Object> dmap = new HashMap<String, Object>();
				  dmap.put("name", loreclass.getName());
				  
				  Loreclass bean = new Loreclass();
				  bean.update(dmap , loreclass.getId());
				  String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
			}
			  
			
		}else 
		{


			Loreclass bean = new Loreclass();
			List<Loreclass> list = (List<Loreclass>) bean.list();
			root.put("list", list);
			printFreemarker("admin/loreclass.ftl", root);
			
		}
		
	}
	
	/**
	 * 药品分类操作
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void drugclass() throws IllegalAccessException, InvocationTargetException
	{
		
		if(request.isSubmit())
		{
			Drugclass drugclass= new Drugclass();
			Map map = request.getParameterMap();
			BeanUtils.populate(drugclass , map);
			if(request.getParameter("sub").equals("save"))
			{
				
				if(drugclass.get_parentId()==0)
				{
					drugclass.setLevel(1);
					drugclass.setState("closed");
				}else {
					drugclass.setLevel(2);
				}
				drugclass.save();
				 String json = "{\"success\": true,   \"message\": \"添加成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				
				if(drugclass.get_parentId()==0)
				{
					drugclass.setLevel(1);
					drugclass.setState("closed");
				}else {
					drugclass.setLevel(2);
					drugclass.setState(null);
				}
				Drugclass bean = new Drugclass();
				  Map<String, Object> dmap = new HashMap<String, Object>();
				  dmap.put("title", drugclass.getTitle());
				  dmap.put("_parentId", drugclass.get_parentId());
				  dmap.put("state", drugclass.getState());
				  dmap.put("level", drugclass.getLevel());
				  bean.update(dmap, drugclass.getId());
				  String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
				
			}
				
			  
			
		}else 
		{
			//Directory bean = new Directory();
			//List<Directory> list =(List<Directory>) bean.list(); 
			//root.put("list", list);
			
			Drugclass bean = new Drugclass();
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("level", 1);
			List<Drugclass> roots = (List<Drugclass>) bean.getlist(map );
			root.put("roots", roots);
			printFreemarker("admin/drugclass.ftl", root);
		}
	}
	
	
	/**
	 * 疾病分类操作
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void diseaseclass() throws IllegalAccessException, InvocationTargetException
	{
		
		if(request.isSubmit())
		{
			Diseaseclass diseaseclass= new Diseaseclass();
			Map map = request.getParameterMap();
			BeanUtils.populate(diseaseclass , map);
			if(request.getParameter("sub").equals("save"))
			{
				
				if(diseaseclass.get_parentId()==0)
				{
					diseaseclass.setLevel(1);
					diseaseclass.setState("closed");
				}else {
					diseaseclass.setLevel(2);
				}
				diseaseclass.save();
				 String json = "{\"success\": true,   \"message\": \"添加成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				
				if(diseaseclass.get_parentId()==0)
				{
					diseaseclass.setLevel(1);
					diseaseclass.setState("closed");
				}else {
					diseaseclass.setLevel(2);
					diseaseclass.setState(null);
				}
					Diseaseclass bean = new Diseaseclass();
				  Map<String, Object> dmap = new HashMap<String, Object>();
				  dmap.put("title", diseaseclass.getTitle());
				  dmap.put("_parentId", diseaseclass.get_parentId());
				  dmap.put("state", diseaseclass.getState());
				  dmap.put("level", diseaseclass.getLevel());
				  bean.update(dmap, diseaseclass.getId());
				  String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
				
			}
				
			  
			
		}else 
		{
			//Directory bean = new Directory();
			//List<Directory> list =(List<Directory>) bean.list(); 
			//root.put("list", list);
			
			Diseaseclass bean = new Diseaseclass();
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("level", 1);
			List<Diseaseclass> roots = (List<Diseaseclass>) bean.getlist(map );
			root.put("roots", roots);
			printFreemarker("admin/diseaseclass.ftl", root);
		}
	}
	
	/**
	 * 病状分类操作
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void symptomclass() throws IllegalAccessException, InvocationTargetException
	{
		
		if(request.isSubmit())
		{
			Symptomclass symptomclass= new Symptomclass();
			Map map = request.getParameterMap();
			BeanUtils.populate(symptomclass , map);
			if(request.getParameter("sub").equals("save"))
			{
				
				if(symptomclass.get_parentId()==0)
				{
					symptomclass.setLevel(1);
					symptomclass.setState("closed");
				}else {
					symptomclass.setLevel(2);
				}
				symptomclass.save();
				 String json = "{\"success\": true,   \"message\": \"添加成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				
				if(symptomclass.get_parentId()==0)
				{
					symptomclass.setLevel(1);
					symptomclass.setState("closed");
				}else {
					symptomclass.setLevel(2);
					symptomclass.setState(null);
				}
				Symptomclass bean = new Symptomclass();
				  Map<String, Object> dmap = new HashMap<String, Object>();
				  dmap.put("title", symptomclass.getTitle());
				  dmap.put("_parentId", symptomclass.get_parentId());
				  dmap.put("state", symptomclass.getState());
				  dmap.put("level", symptomclass.getLevel());
				  bean.update(dmap, symptomclass.getId());
				  String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
				
			}
				
			  
			
		}else 
		{
			//Directory bean = new Directory();
			//List<Directory> list =(List<Directory>) bean.list(); 
			//root.put("list", list);
			
			Symptomclass bean = new Symptomclass();
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("level", 1);
			List<Symptomclass> roots = (List<Symptomclass>) bean.getlist(map );
			root.put("roots", roots);
			printFreemarker("admin/symptomclass.ftl", root);
		}
	}
	
	/**
	 * 显示没有审核的药品
	 */
	public void symptom()
	{
		List<Symptoms> list = symptomService.getNoCheck();
		root.put("list", list);
		printFreemarker("admin/symptom_check_list.ftl", root);
	}
	
	
	
	/*
	 * 
	 */
	public void disease()
	{
		List<Disease> list = diseaseService.getNoCheck();
		root.put("list", list);
		printFreemarker("admin/disease_check_list.ftl", root);
	}
	public void jsondrugclass(){
		Drugclass bean = new Drugclass();
		List<Drugclass> rows = (List<Drugclass>) bean.list();
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		json= "{\"rows\":"+json+"}";
		printJson(json);
		 
	}
	public void jsondiseaseclass(){
		Diseaseclass bean = new Diseaseclass();
		List<Diseaseclass> rows = (List<Diseaseclass>) bean.list();
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		json= "{\"rows\":"+json+"}";
		printJson(json);
		 
	}
	public void jsonsymptomclass(){
		Symptomclass bean = new Symptomclass();
		List<Symptomclass> rows = (List<Symptomclass>) bean.list();
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		json= "{\"rows\":"+json+"}";
		printJson(json);
		 
	}
	
	
	/**
	 * 生产厂家
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void factory() throws IllegalAccessException, InvocationTargetException
	{
		if(request.isSubmit())
		{
			Factory factory = new Factory();
			Map map = request.getParameterMap();
			BeanUtils.populate(factory, map);
			if (request.getParameter("sub").equals("save")) 
			{
				
					factory.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
				
			}else if (request.getParameter("sub").equals("edit"))
			{
				Factory bean = new Factory();
				Map<String, Object> vmap = new HashMap<String, Object>();
				vmap.put("name", factory.getName());
				vmap.put("description", factory.getDescription());
				vmap.put("phone", factory.getPhone());
				vmap.put("url", factory.getUrl());
				vmap.put("address", factory.getAddress());
				bean.update(vmap , factory.getId());
				 String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
			}
		}
		else{
			List<Factory> list = factoryService.getAll();
			root.put("list", list);
			printFreemarker("admin/factory.ftl", root);
		}
		
	}
	
	
	/**
	 * 友情链接
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void links() throws IllegalAccessException, InvocationTargetException
	{
		if(request.isSubmit())
		{
			Links links = new Links();
			Map map = request.getParameterMap();
			BeanUtils.populate(links, map);
			if (request.getParameter("sub").equals("save")) 
			{
				
					links.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
				
			}else if (request.getParameter("sub").equals("edit"))
			{
				Links bean = new Links();
				Map<String, Object> vmap = new HashMap<String, Object>();
				vmap.put("title", links.getTitle());
				vmap.put("url", links.getUrl());
				vmap.put("focal", links.getFocal());
				vmap.put("sequence", links.getSequence());
				bean.update(vmap , links.getId());
				 String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
			}
		}
		else{
			List<Links> list = linksService.getAll();
			root.put("list", list);
			printFreemarker("admin/links.ftl", root);
		}
	}
	
	
	/**
	 * 合作伙伴
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void partner() throws IllegalAccessException, InvocationTargetException
	{
		if(request.isSubmit())
		{
			Partner partner  = new Partner();
			Map map = request.getParameterMap();
			BeanUtils.populate(partner, map);
			if (request.getParameter("sub").equals("save")) 
			{
				
				partner.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
				
			}else if (request.getParameter("sub").equals("edit"))
			{
				Partner bean = new Partner();
				Map<String, Object> vmap = new HashMap<String, Object>();
				vmap.put("title", partner.getTitle());
				vmap.put("url", partner.getUrl());
				vmap.put("description", partner.getDescription());
				vmap.put("sequence", partner.getSequence());
				bean.update(vmap , partner.getId());
				 String json = "{\"success\": true,   \"message\": \"修改成功.\" } ";
				  printHtml(json);
				  return;
			}
		}
		else{
			List<Partner> list = partnerService.getAll();
			root.put("list", list);
			printFreemarker("admin/partner.ftl", root);
		}
	}
	
	
	/**
	 * 显示没有审核的药品
	 */
	public void drug()
	{
		List<Drug> list = drugService.getNoCheck();
		root.put("list", list);
		printFreemarker("admin/drug_check_list.ftl", root);
	}
	
	
	/*
	 * 读取需要显示的药品信息
	 */
	public void checkdrug()
	{
		
			String[] params = request.getParams();
			if(params!=null)
			{
				Long id = Long.parseLong(params[0]);
				Drug bean = new Drug();
				Drug drug = bean.get(id);
				List<DrugInfo> list = drugInfoService.getDrugInfo(id);
				List<Factory> factorys = factoryService.getAll();
				root.put("drug", drug);
				root.put("list", list);
				root.put("factorys", factorys);
				
				Drugclass dbean = new Drugclass();
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("level", 2);
				List<Drugclass> drugclassess = (List<Drugclass>) dbean.getlist(map );
				
				root.put("drugclass", drugclassess);
				printFreemarker("admin/drug_check.ftl", root);
			}
		
		
	}
	
	/**
	 * 核对病状
	 */
	public void checksymptom()
	{
		
			String[] params = request.getParams();
			if(params!=null)
			{
				Long id = Long.parseLong(params[0]);
				Symptoms bean = new Symptoms();
				Symptoms syptoms = bean.get(id);
				
				List<SymptomInfo> list = symptomInfoService.getSymptomInfo(id);
				
				root.put("syptoms", syptoms);
				root.put("list", list);
				
				Symptomclass sbean = new Symptomclass();
				
//				Drugclass dbean = new Drugclass();
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("level", 2);
				List<Symptomclass> symptomclass = (List<Symptomclass>) sbean.getlist(map );
//				
				root.put("symptomclass", symptomclass);
				printFreemarker("admin/symptom_check.ftl", root);
			}
		
		
	}
	
	public void checkdisease()
	{
		
			String[] params = request.getParams();
			if(params!=null)
			{
				Long id = Long.parseLong(params[0]);
				Disease bean = new Disease();
				Disease disease = bean.get(id);
				
				List<DiseaseInfo> list = diseaseInfoService.getDiseaseInfo(id);
				
				root.put("disease", disease);
				root.put("list", list);
				
				Diseaseclass sbean = new Diseaseclass();
				
//				Drugclass dbean = new Drugclass();
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("level", 2);
				List<Diseaseclass> diseaseclasses = (List<Diseaseclass>) sbean.getlist(map );
//				
				root.put("diseaseclass", diseaseclasses);
				printFreemarker("admin/diseaseclass_check.ftl", root);
			}
		
		
	}
	
	public void news()
	{
		root.put("list", newsService.getNoCheck());
		printFreemarker("admin/news_check_list.ftl", root);
	}
	
	public void lore()
	{
		root.put("list", loreService.getNoCheck());
		printFreemarker("admin/lore_check_list.ftl", root);
	}
	
	
	public void checknews() throws IllegalAccessException, InvocationTargetException 
	{
		if (request.isSubmit())
		{
			News bean = new News();
			Map map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			
			Map<String, Object> vmap = new HashMap<String, Object>();
			vmap.put("title", bean.getTitle());
			vmap.put("message", bean.getMessage());
			vmap.put("author", bean.getAuthor());
			vmap.put("allow", NewsEnum.Check_Status.IsCheck.getValue());
			bean.update(vmap , bean.getId());
			sendRedirect(request.basePath()+"admin/news");
			
		}else 
		{
		
			String sid= request.getParams()[0];
			News news = new News();
			news = news.get(Long.parseLong(sid));
			root.put("news", news);
			printFreemarker("admin/news_check.ftl", root);
		}
	}
	
	
	public void checkrole() throws IllegalAccessException, InvocationTargetException 
	{
		if (request.isSubmit())
		{
			Lore bean = new Lore();
			Map map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			
			Map<String, Object> vmap = new HashMap<String, Object>();
			vmap.put("title", bean.getTitle());
			vmap.put("loreclass", bean.getLoreclass());
			vmap.put("message", bean.getMessage());
			vmap.put("author", bean.getAuthor());
			vmap.put("allow", NewsEnum.Check_Status.IsCheck.getValue());
			bean.update(vmap , bean.getId());
			sendRedirect(request.basePath()+"admin/lore");
			
		}else 
		{
		
			String sid= request.getParams()[0];
			Lore lore = new Lore();
			lore = lore.get(Long.parseLong(sid));
			Loreclass bean = new Loreclass();
			List<Loreclass> list = (List<Loreclass>) bean.list();
			root.put("lore", lore);
			root.put("list", list);
			printFreemarker("admin/lore_check.ftl", root);
		}
	}
	
	
	public void login() throws IllegalAccessException, InvocationTargetException
	{
		if (request.isSubmit())
		{
			Map map = request.getParameterMap();
			  User vuser = new User();
			  BeanUtils.populate(vuser , map);
			  User bean = new User();
			  Map<String, Object> vmap = new HashMap<String, Object>();
			  vmap.put("account", vuser.getAccount());
			  vmap.put("password", vuser.getPassword());
			  User user = bean.get(vmap );
			  if (user!=null)
			  {
				  //session.setUser(user);
				  sendRedirect(request.basePath()+"admin");
				  return;
			  }else {
				
			
				root.put("message", "你登录的账号或密码错误");
				printFreemarker("default/login.ftl", root);
				return;
			  }
			
		}else {
			printFreemarker("default/login.ftl", root);
		}
		
	}
	
	
	private DirectoryService directoryService = new DirectoryService ();
	private FactoryService factoryService = new FactoryService();
	private LinksService linksService = new LinksService();
	private PartnerService partnerService = new PartnerService();
	private DrugService drugService = new DrugService();
	private DrugInfoService drugInfoService = new DrugInfoService();
	private NewsService newsService = new NewsService();
	private LoreService loreService = new LoreService();
	private SymptomService symptomService = new SymptomService();
	private SymptomInfoService symptomInfoService = new SymptomInfoService();
	private DiseaseService diseaseService = new DiseaseService();
	private DiseaseInfoService diseaseInfoService = new DiseaseInfoService();
	
}
