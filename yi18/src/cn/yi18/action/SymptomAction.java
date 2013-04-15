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
import cn.yi18.util.PageUtil;

public class SymptomAction extends BaseAction
{
	private final int SIZE=10;
	
	
	public void list() throws ServletException, IOException {
		
		int page= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		// 取得页面，如果没有默认为1
		List<DrugClass> tree = drugClassService.getTree();
		root.put("tree", tree);
		
		String[] params = request.getParams();
		if(params==null)
		{
			List<Drug> news = drugService.getNew(SIZE);
			PageUtil hots = drugService.getPageHot(page, SIZE);
			root.put("id", 0);
			root.put("news", news);
			root.put("page", hots);
			root.put("open", tree.get(tree.size()-1).getDrugclass().getId());
			
		}else 
		{
			long id = Long.parseLong(params[0]);//药品分类的id
			Drugclass bean = new Drugclass();
			Drugclass drugclass = bean.get(id); //取得分类
			
			
			List<Drug> news = drugService.getNew(SIZE,id);
			PageUtil hots = drugService.getPageHot(page, SIZE,id);
			root.put("id", id);
			root.put("news", news);
			root.put("page", hots);
			root.put("drugclass",drugclass);
			root.put("open", drugclass.get_parentId());//打开的栏目
		}
		
		
		//root.put("open", 1);
		printFreemarker("default/drug_list.ftl", root);
	}
	
	
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
	 * 更新药品信息
	 * @throws FileUploadException
	 */
	public void update() throws FileUploadException
	{
		
			
			Drug drug = new Drug(); //药品
			List<Druginfo> druginfos = new ArrayList<Druginfo>();//药品信息
			//最大文件大小
			long maxSize = 1000000;
			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			
			//String urlPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			String readlityPath=request.getSession().getServletContext().getRealPath("/");
			//String saveUrl=urlPath+"common/temp/";
			String savePath=readlityPath+"common/avatar/";
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
				    String name = item.getFieldName();
				    String value = null;
					try {
						value = item.getString("UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(name.equals("id"))
					{
						   drug.setId(Long.parseLong(value));
					}else
				   if(name.equals("name"))
				   {
					   drug.setName(value);
				   }else if(name.equals("alias")){
					   drug.setAlias(value);
					
				   }else if(name.equals("term")){
					   drug.setTerm(value);
					
				   }else if(name.equals("prescription")){
					   drug.setPrescription(Integer.parseInt(value));
					
				   }else if(name.equals("ingredient")){
					   drug.setIngredient(Integer.parseInt(value));
					
				   
				  }else if(name.equals("factory")){
					   drug.setFactory(Long.parseLong(value));
					
				   }
				   else if(name.equals("price")){
					   drug.setPrice(Float.parseFloat(value));
					
				   } else if(name.equals("drugclass")){
					   drug.setDrugclass(Long.parseLong(value));
					
				   }else if(name.startsWith("editor"))
				   {
					   Druginfo druginfo = new Druginfo();
					   druginfo.setMessage(value);   
					   //druginfo.setDirectory(Long.parseLong(name.split("_")[1]));
					   druginfo.setId(Long.parseLong(name.split("_")[1]));
					   druginfos.add(druginfo);
					
				   }
				   

				   
				}else{
					if(item.getSize() > maxSize){
						
						return;
					}
					String fileName = item.getName();	
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if(!Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
						
						//return;
					}

					String newFileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + "_" + new Random().nextInt(1000) + "." + fileExt;
					drug.setImage(newFileName);
						File uploadedFile = new File(savePath, newFileName);
						try {
							item.write(uploadedFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//					
					
				}
				
				
			}
			
			
			
			/*
			 * 更新数据
			 */
			drugService.update(drug, druginfos);
			sendRedirect(request.basePath()+"admin/drug");
			return;
			
	}
	
	
	/**
	 * 显示药品信息
	 */
	public void show()
	{
		String[] params = request.getParams();
		if(params!=null)
		{
			Long id = Long.parseLong(params[0]);
			Drug bean = new Drug();
			Drug drug = bean.get(id);
			List<DrugInfo> list = drugInfoService.getDrugInfo(id);
			root.put("drug", drug);
			root.put("list", list);
			
			printFreemarker("default/drug.ftl", root);
		}
	}
	
	
	private DirectoryService directoryService = new DirectoryService();
	private DrugService drugService = new DrugService();
	private DrugInfoService drugInfoService = new DrugInfoService();
	private FactoryService factoryService = new FactoryService();
	private DrugClassService  drugClassService = new DrugClassService();
	
	
}
