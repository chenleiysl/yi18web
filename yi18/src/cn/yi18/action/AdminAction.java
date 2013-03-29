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
import cn.yi18.pojo.POJO;
import cn.yi18.service.DirectoryService;

public class AdminAction extends BaseAction {

	
	@Override
	public void execute() throws ServletException, IOException {
		printFreemarker("default/admin.ftl", root);
	}
	
	public void directory() throws IllegalAccessException, InvocationTargetException {
		
		if(request.isSubmit())
		{
			if(request.getParameter("sub").equals("save"))
			{
				Map map = request.getParameterMap();
				  Directory directory = new Directory();
				  BeanUtils.populate(directory , map);
				  directory.save();
				  String json = "{\"success\": true,   \"message\": \"保存成功.\" } ";
				  printHtml(json);
				  return;
				
			}else if(request.getParameter("sub").equals("edit"))
			{
				Map map = request.getParameterMap();
				  Directory directory = new Directory();
				  BeanUtils.populate(directory , map);
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
	
	private DirectoryService directoryService = new DirectoryService ();
}
