package cn.yi18.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;


import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


import cn.yi18.entity.DiseaseInfo;
import cn.yi18.entity.DrugInfo;
import cn.yi18.entity.SymptomInfo;
import cn.yi18.enums.DirectoryEnum;
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
import cn.yi18.util.Base64Coder;
import cn.yi18.util.DigestMD;
import cn.yi18.util.DigestSHA;


/**
 * 管理员的操作，主要是后台的操作
 * @author 陈磊
 *
 */
public class AdminAction extends BaseAction {

	private static final Logger log= LoggerFactory.getLogger(AdminAction.class);
	/**
	 * 默认admin页面
	 */
	@Override
	public void execute() throws ServletException, IOException
	{
		
		printFreemarker("admin/index.ftl", root);
	}
	
	
	/**
	 * 目录信息 主要保存药品、疾病、病状的信息
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void directory() throws IllegalAccessException, InvocationTargetException {
		
		if(request.isSubmit())
		{
			Map<?, ?> map = request.getParameterMap();
			Directory directory = new Directory();
			BeanUtils.populate(directory , map);
			if(request.getParameter("sub").equals("save"))//保存目录信息
			{
				
				  directory.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))//编辑目录信息
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
			if(type.equals(DirectoryEnum.Type.Drug.getValue()+""))//显示药品的目录
			{
				List<Directory> list =directoryService.getDrug(); 
				root.put("list", list);
				printFreemarker("admin/directory_drug.ftl", root);
			}else if(type.equals(DirectoryEnum.Type.Symptom.getValue()+""))//显示病状目录
			{
				List<Directory> list =directoryService.getSymptom(); 
				root.put("list", list);
				printFreemarker("admin/directory_symptom.ftl", root);
			}else if(type.equals(DirectoryEnum.Type.Disease.getValue()+""))//显示疾病目录
			{
				List<Directory> list =directoryService.getDisease(); 
				root.put("list", list);
				printFreemarker("admin/directory_disease.ftl", root);
			}
		}
		
	}
	
	
	/**
	 * 人的身体部位
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void place() throws IllegalAccessException, InvocationTargetException {
		
	
		if(request.isSubmit())
		{
			Map<?, ?> map = request.getParameterMap();
			Place place = new Place();
			BeanUtils.populate(place , map);
			if(request.getParameter("sub").equals("save"))//保存身体部位
			{
				
				  place.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))//编辑身体部位
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
			//显示身体部位
			Place bean = new Place();
			@SuppressWarnings("unchecked")
			List<Place> list = (List<Place>) bean.list();
			root.put("list", list);
			printFreemarker("admin/place.ftl", root);
			
		}
		
	}
	
	
	/**
	 * 就诊科室
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void department() throws IllegalAccessException, InvocationTargetException {
		
		
		if(request.isSubmit())
		{
			Map<?, ?> map = request.getParameterMap();
			Departments departments = new Departments();
			BeanUtils.populate(departments , map);
			if(request.getParameter("sub").equals("save"))//保存
			{
				
				 departments.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))//编辑
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
			@SuppressWarnings("unchecked")
			List<Place> list = (List<Place>) bean.list();
			root.put("list", list);
			printFreemarker("admin/departments.ftl", root);
			
		}
		
	}
	
	
	/**
	 * 健康知识分类
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void loreclass() throws IllegalAccessException, InvocationTargetException {
		
		
		if(request.isSubmit())
		{
			Map<?, ?> map = request.getParameterMap();
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
			@SuppressWarnings("unchecked")
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
			Map<?, ?> map = request.getParameterMap();
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
		
			Drugclass bean = new Drugclass();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("level", 1);
			@SuppressWarnings("unchecked")
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
			Map<?, ?> map = request.getParameterMap();
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
			Diseaseclass bean = new Diseaseclass();
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("level", 1);
			@SuppressWarnings("unchecked")
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
			Map<?, ?> map = request.getParameterMap();
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
			
			Symptomclass bean = new Symptomclass();
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("level", 1);
			@SuppressWarnings("unchecked")
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
	 * 显示没有审核的疾病
	 */
	public void disease()
	{
		List<Disease> list = diseaseService.getNoCheck();
		root.put("list", list);
		printFreemarker("admin/disease_check_list.ftl", root);
	}
	
	/**
	 * 取得药品分类
	 */
	public void jsondrugclass(){
		Drugclass bean = new Drugclass();
		@SuppressWarnings("unchecked")
		List<Drugclass> rows = (List<Drugclass>) bean.list();
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		json= "{\"rows\":"+json+"}";
		printJson(json);
		 
	}
	/**
	 * 取得疾病分类
	 */
	public void jsondiseaseclass(){
		Diseaseclass bean = new Diseaseclass();
		@SuppressWarnings("unchecked")
		List<Diseaseclass> rows = (List<Diseaseclass>) bean.list();
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		json= "{\"rows\":"+json+"}";
		printJson(json);
		 
	}
	/**
	 * 取得病状分类
	 */
	public void jsonsymptomclass(){
		Symptomclass bean = new Symptomclass();
		@SuppressWarnings("unchecked")
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
			Map<?, ?> map = request.getParameterMap();
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
			Map<?, ?> map = request.getParameterMap();
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
			Map<?, ?> map = request.getParameterMap();
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
				@SuppressWarnings("unchecked")
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
				@SuppressWarnings("unchecked")
				List<Symptomclass> symptomclass = (List<Symptomclass>) sbean.getlist(map );
//				
				root.put("symptomclass", symptomclass);
				printFreemarker("admin/symptom_check.ftl", root);
			}
		
		
	}
	
	/**
	 * 审核疾病
	 */
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
				@SuppressWarnings("unchecked")
				List<Diseaseclass> diseaseclasses = (List<Diseaseclass>) sbean.getlist(map );
//				
				root.put("diseaseclass", diseaseclasses);
				
				Place place = new Place(); //身体部位
				@SuppressWarnings("unchecked")
				List<Place> places = (List<Place>) place.list();
				
				Departments department = new Departments();//科室
				@SuppressWarnings("unchecked")
				List<Departments> departments = (List<Departments>) department.list();
				root.put("places", places);
				root.put("departments", departments);
				printFreemarker("admin/disease_check.ftl", root);
			}
		
		
	}
	
	/**
	 * 显示综合，主要是核对的新闻投递
	 */
	public void news()
	{
		root.put("list", newsService.getNoCheck());
		printFreemarker("admin/news_check_list.ftl", root);
	}
	
	/**
	 * 显示没有审核的健康知识
	 */
	public void lore()
	{
		root.put("list", loreService.getNoCheck());
		printFreemarker("admin/lore_check_list.ftl", root);
	}
	
	/**
	 * 核对显示的新闻
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void checknews() 
	{

			String sid= request.getParams()[0];
			News news = new News();
			news = news.get(Long.parseLong(sid));
			root.put("news", news);
			printFreemarker("admin/news_check.ftl", root);

	}
	
	/**
	 * 核对健康知识
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void checkrole() throws IllegalAccessException, InvocationTargetException 
	{
	
			String sid= request.getParams()[0];
			Lore lore = new Lore();
			lore = lore.get(Long.parseLong(sid));
			Loreclass bean = new Loreclass();
			@SuppressWarnings("unchecked")
			List<Loreclass> list = (List<Loreclass>) bean.list();
			root.put("lore", lore);
			root.put("list", list);
			printFreemarker("admin/lore_check.ftl", root);
		
	}
	
	
	/**
	 * 登录
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void login() throws IllegalAccessException, InvocationTargetException
	{
		
		String returnUrl = Base64Coder.decodeBase64(request.getParameter("returnUrl"));
		if (request.isSubmit())
		{
			
			Map<?, ?> map = request.getParameterMap();
			  User vuser = new User();
			  BeanUtils.populate(vuser , map);
			  User bean = new User();
			  Map<String, Object> vmap = new HashMap<String, Object>();
			  vmap.put("account", vuser.getAccount());
			  vmap.put("password",DigestMD.MD5(vuser.getPassword()));//MD5加密
			  User user = bean.get(vmap );
			  if (user!=null)
			  {
				  String hkey=DigestSHA.SHA224(user.getAccount()+user.getPassword());
				  response.addAutoLoginCookie(1800, hkey);//30
				  session.setUser(user);
				  log.debug("用户{}在{}登录后台系统",user.getAccount(),new Date());
				  sendRedirect(returnUrl);
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
	

	/**
	 * 退出
	 */
	public void exit()
	{
		
		String returnUrl = Base64Coder.decodeBase64(request.getParameter("returnUrl"));
		if(returnUrl==null) returnUrl=request.basePath();
		String session_id= request.getParams()!=null?request.getParams()[0]:null;
		User user = session.getUser(yi18_id); 
		if (request.getSession().getId().equals(session_id))
		{
			//退出的session_id做比较，目的是为了防止用退出连接做攻击，实现每个用户的退出连接不同
			 log.debug("用户{}在{}退出后台系统",user.getAccount(),new Date());
			session.deleteUser(yi18_id);
			_removeAuto(user);
		}
		else
		{
			sendRedirect(returnUrl);
		}
		
		sendRedirect(request.basePath());
		return;
	
	}
	
	/**
	 * 移除cookie
	 * @param user
	 */
	  public void _removeAuto(User user) 
	  {
		  response.removeAutoLoginCookie();
	
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
