package cn.yi18.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.Factory;
import cn.yi18.pojo.Links;
import cn.yi18.pojo.POJO;
import cn.yi18.pojo.Partner;
import cn.yi18.service.DirectoryService;
import cn.yi18.service.FactoryService;
import cn.yi18.service.LinksService;
import cn.yi18.service.PartnerService;

public class AdminAction extends BaseAction {

	
	@Override
	public void execute() throws ServletException, IOException {
		printFreemarker("default/admin.ftl", root);
	}
	
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
			
			List<Directory> list =directoryService.getAll(); 
			root.put("list", list);
			printFreemarker("default/directory.ftl", root);
		}
		
	}
	
	
	public void drugclass() throws IllegalAccessException, InvocationTargetException
	{
		
		if(request.isSubmit())
		{
			if(request.getParameter("sub").equals("save"))
			{
				Drugclass drugclass= new Drugclass();
				Map map = request.getParameterMap();
				BeanUtils.populate(drugclass , map);
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
				Drugclass drugclass= new Drugclass();
				Map map = request.getParameterMap();
				BeanUtils.populate(drugclass , map);
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
			printFreemarker("default/drugclass.ftl", root);
		}
	}
	
	public void jsondrugclass(){
		Drugclass bean = new Drugclass();
		List<Drugclass> rows = (List<Drugclass>) bean.list();
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		json= "{\"rows\":"+json+"}";
		printJson(json);
		 
	}
	
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
			printFreemarker("default/factory.ftl", root);
		}
		
	}
	
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
			printFreemarker("default/links.ftl", root);
		}
	}
	
	
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
			printFreemarker("default/partner.ftl", root);
		}
	}
	
	
	private DirectoryService directoryService = new DirectoryService ();
	private FactoryService factoryService = new FactoryService();
	private LinksService linksService = new LinksService();
	private PartnerService partnerService = new PartnerService();
}
