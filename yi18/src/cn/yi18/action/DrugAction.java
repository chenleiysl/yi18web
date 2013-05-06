package cn.yi18.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.yi18.entity.DrugClass;
import cn.yi18.entity.DrugInfo;
import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.Factory;
import cn.yi18.pojo.POJO;
import cn.yi18.service.DirectoryService;
import cn.yi18.service.DrugClassService;
import cn.yi18.service.DrugInfoService;
import cn.yi18.service.DrugService;
import cn.yi18.service.FactoryService;
import cn.yi18.util.JsoupUtil;
import cn.yi18.util.PageUtil;

public class DrugAction extends BaseAction
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
	 * 添加药品信息
	 * @throws FileUploadException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void add() throws FileUploadException, IllegalAccessException, InvocationTargetException
	{
		if(request.isSubmit())
		{
			
			Drug drug = new Drug(); //药品
			List<Druginfo> druginfos = new ArrayList<Druginfo>();//药品信息
			
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(drug, map);
			
			//从词条中取得一张图片
//			Document doc= Jsoup.parse(drug.getTerm());
//			Elements imgs = doc.select("img");
//			for(int i=0;i<imgs.size();i++){
//				  Element img = imgs.get(i);
//					String src = img.attr("src");
//					
//					//File file = new File(request.getSession().getServletContext().getRealPath("../"));
//					drug.setImage(src);//
//					break;
//			}
			
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
			    String name= names.nextElement();
			    
			    if(name.startsWith("editor"))//添加药品的内容
				   {
			    	 	Druginfo druginfo = new Druginfo();
					   druginfo.setMessage(request.getParameter(name));   
					   druginfo.setDirectory(Long.parseLong(name.split("_")[1]));
					   druginfos.add(druginfo);
			    	
				   }
			}
			/*
			 * 保存数据
			 */
			drugService.save(drug, druginfos);
			root.put("message", "添加成功！等待审核！");
			printFreemarker("default/message.ftl", root);
			return;
			
		}else {
			List<Factory> factorys = factoryService.getAll();//取得药品的生产厂家
			List<Directory> list = directoryService.getDrug();//取得药品内容的目录
			root.put("list", list);
			root.put("factorys", factorys);
			
			root.put("title", "医药吧 | 添加药品信息");
			printFreemarker("default/add_drug.ftl", root);
			
		}
	}
	
	
	/**
	 * 更新药品信息
	 * @throws FileUploadException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void update() throws FileUploadException, IllegalAccessException, InvocationTargetException
	{
		
			
//			Drug drug = new Drug(); //药品
//			List<Druginfo> druginfos = new ArrayList<Druginfo>();//药品信息
//			//最大文件大小
//			long maxSize = 1000000;
//			//定义允许上传的文件扩展名
//			HashMap<String, String> extMap = new HashMap<String, String>();
//			extMap.put("image", "gif,jpg,jpeg,png,bmp");
//			
//			//String urlPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
//			String readlityPath=request.getSession().getServletContext().getRealPath("/");
//			//String saveUrl=urlPath+"common/temp/";
//			String savePath=readlityPath+"common/avatar/";
//			FileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			upload.setHeaderEncoding("UTF-8");
//			List items = upload.parseRequest(request);
//			Iterator itr = items.iterator();
//			while (itr.hasNext()) {
//				FileItem item = (FileItem) itr.next();
//				if (item.isFormField()) {
//				    String name = item.getFieldName();
//				    String value = null;
//					try {
//						value = item.getString("UTF-8");
//					} catch (UnsupportedEncodingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					if(name.equals("id"))
//					{
//						   drug.setId(Long.parseLong(value));
//					}else
//				   if(name.equals("name"))
//				   {
//					   drug.setName(value);
//				   }else if(name.equals("alias")){
//					   drug.setAlias(value);
//					
//				   }else if(name.equals("term")){
//					   drug.setTerm(value);
//					
//				   }else if(name.equals("prescription")){
//					   drug.setPrescription(Integer.parseInt(value));
//					
//				   }else if(name.equals("ingredient")){
//					   drug.setIngredient(Integer.parseInt(value));
//					
//				   
//				  }else if(name.equals("factory")){
//					   drug.setFactory(Long.parseLong(value));
//					
//				   }
//				   else if(name.equals("price")){
//					   drug.setPrice(Float.parseFloat(value));
//					
//				   } else if(name.equals("drugclass")){
//					   drug.setDrugclass(Long.parseLong(value));
//					
//				   }else if(name.startsWith("editor"))
//				   {
//					   Druginfo druginfo = new Druginfo();
//					   druginfo.setMessage(value);   
//					   //druginfo.setDirectory(Long.parseLong(name.split("_")[1]));
//					   druginfo.setId(Long.parseLong(name.split("_")[1]));
//					   druginfos.add(druginfo);
//					
//				   }
//				   
//
//				   
//				}else{
//					if(item.getSize() > maxSize){
//						
//						return;
//					}
//					String fileName = item.getName();	
//					//检查扩展名
//					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//					if(!Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
//						
//						//return;
//					}
//
//					String newFileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + "_" + new Random().nextInt(1000) + "." + fileExt;
//					drug.setImage(newFileName);
//						File uploadedFile = new File(savePath, newFileName);
//						try {
//							item.write(uploadedFile);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
////					
//					
//				}
//				
//				
//			}
			
			
		
		Drug drug = new Drug(); //药品
		List<Druginfo> druginfos = new ArrayList<Druginfo>();//药品信息
		
		Map<String, String[]> map = request.getParameterMap();
		BeanUtils.populate(drug, map);
		
		//从词条中取得一张图片
//		Document doc= Jsoup.parse(drug.getTerm());
//		Elements imgs = doc.select("img");
//		for(int i=0;i<imgs.size();i++){
//			  Element img = imgs.get(i);
//				String src = img.attr("src");
//				
//				//File file = new File(request.getSession().getServletContext().getRealPath("../"));
//				drug.setImage(src);//
//				break;
//		}
		drug.setImage(JsoupUtil.Image(drug.getTerm()));
		
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		    String name= names.nextElement();
		    
		    if(name.startsWith("editor"))//添加药品的内容
			   {
		    	 	Druginfo druginfo = new Druginfo();
//				   druginfo.setMessage(request.getParameter(name));   
//				   druginfo.setDirectory(Long.parseLong(name.split("_")[1]));
//				   druginfos.add(druginfo);

				   druginfo.setMessage(request.getParameter(name));   
				   //druginfo.setDirectory(Long.parseLong(name.split("_")[1]));
				   druginfo.setId(Long.parseLong(name.split("_")[1]));
				   druginfos.add(druginfo);
		    	
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
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("count", drug.getCount()+1);
			bean.update(map , id);
			List<DrugInfo> list = drugInfoService.getDrugInfo(id);
			root.put("drug", drug);
			root.put("list", list);
			root.put("drugclass", _Drugclass(drug.getDrugclass()));//取得药品的分类
			root.put("factory", _Factory(drug.getFactory()));//取得药品的生产厂家
			
			root.put("title", drug.getName()+","+drug.getAlias()+"_医药吧");
			 root.put("keywords", drug.getName()+","+drug.getAlias()+",说明书,使用手册,基本简介,功能描述,医药吧,yi18");
			 root.put("description", drug.subTerm(512));
			 printFreemarker("default/drug.ftl", root);
		}
	}
	
	
	/**
	 * 取得药品的分类
	 * @return
	 */
	private Drugclass _Drugclass(long drugclass) 
	{
		Drugclass bean = new Drugclass();
		return bean.get(drugclass);
		
	}
	/**
	 * 取得药品的厂商
	 * @return
	 */
	private Factory _Factory(long factory) 
	{
		Factory bean = new Factory();
		return bean.get(factory);
		
	}
	
	private DirectoryService directoryService = new DirectoryService();
	private DrugService drugService = new DrugService();
	private DrugInfoService drugInfoService = new DrugInfoService();
	private FactoryService factoryService = new FactoryService();
	private DrugClassService  drugClassService = new DrugClassService();
	
	
}
