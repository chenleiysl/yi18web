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
import cn.yi18.entity.DrugClass;
import cn.yi18.entity.DrugInfo;
import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Drugclass;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.Factory;

import cn.yi18.service.DirectoryService;
import cn.yi18.service.DrugClassService;
import cn.yi18.service.DrugInfoService;
import cn.yi18.service.DrugService;
import cn.yi18.service.FactoryService;
import cn.yi18.util.Base64Coder;
import cn.yi18.util.JsoupUtil;
import cn.yi18.util.PageUtil;

/**
 * 疾病
 * @author 陈磊
 *
 */
public class DrugAction extends BaseAction
{
	private final int SIZE=10;
	private static final Logger log= LoggerFactory.getLogger(DrugAction.class);
	
	/**
	 * 显示疾病信息
	 * @throws ServletException
	 * @throws IOException
	 */
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
			root.put("title", "常见药品|药品信息_医药吧");
			
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
			root.put("title", drugclass.getTitle()+"|药品信息_医药吧");
			root.put("keywords", drugclass.getTitle());
			root.put("description",drugclass.getTitle());
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
			log.debug("添加药品");
			printFreemarker("default/message.ftl", root);
			return;
			
		}else {
			List<Factory> factorys = factoryService.getAll();//取得药品的生产厂家
			List<Directory> list = directoryService.getDrug();//取得药品内容的目录
			root.put("list", list);
			root.put("factorys", factorys);
			
			root.put("title", "添加药品信息|医药吧 ");
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
		

		Drug drug = new Drug(); //药品
		List<Druginfo> druginfos = new ArrayList<Druginfo>();//药品信息
		
		Map<String, String[]> map = request.getParameterMap();
		BeanUtils.populate(drug, map);

		drug.setImage(JsoupUtil.Image(drug.getTerm()));
		
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		    String name= names.nextElement();
		    
		    if(name.startsWith("editor"))//添加药品的内容
			   {
		    	 	Druginfo druginfo = new Druginfo();
			 
				   druginfo.setMessage(request.getParameter(name));   
				  
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
			Drug drug = new Drug();
			drug = drug.get(id);
			if(drug==null){
				run_404();
				return;
			}//如果不存在就返回404页面
			
			
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("count", drug.getCount()+1);
//			bean.update(map , id);
			VisitLogEhCache.Add(drug.getId(), "yi18_drug");//更新阅读数
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
	
	
	 	/**
		 * 删除综药品信息
		 */
		public void delete()
		{
			long id= Long.parseLong(request.getParams()[0]);
			String url =Base64Coder.decodeBase64( request.getParameter("returnUrl")); //取得返回的URL
			Drug drug = new Drug();
			drug.delete(id);
			Druginfo druginfo = new Druginfo();
			String filter = " drug="+id;
			druginfo.delete(filter );
			
			EhCacheEngine.remove("Drugs");
			sendRedirect(url);
		}
	
	private DirectoryService directoryService = new DirectoryService();
	private DrugService drugService = new DrugService();
	private DrugInfoService drugInfoService = new DrugInfoService();
	private FactoryService factoryService = new FactoryService();
	private DrugClassService  drugClassService = new DrugClassService();
	
	
}
