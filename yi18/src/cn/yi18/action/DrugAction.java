package cn.yi18.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.time.DateFormatUtils;

import cn.yi18.entity.DrugInfo;
import cn.yi18.pojo.Directory;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Druginfo;
import cn.yi18.pojo.POJO;
import cn.yi18.service.DirectoryService;
import cn.yi18.service.DrugInfoService;
import cn.yi18.service.DrugService;

public class DrugAction extends BaseAction
{
	public void add() throws FileUploadException
	{
		if(request.isSubmit())
		{
			
			Drug drug = new Drug(); //药品
			List<Druginfo> druginfos = new ArrayList<Druginfo>();//药品信息
			//最大文件大小
			long maxSize = 1000000;
			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			
			String urlPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			String readlityPath=request.getSession().getServletContext().getRealPath("/");
			String saveUrl=urlPath+"common/temp/";
			String savePath=readlityPath+"common/temp/";
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
					
				   }
				   else if(name.equals("price")){
					   drug.setPrice(Float.parseFloat(value));
					
				   }else if(name.startsWith("editor"))
				   {
					   Druginfo druginfo = new Druginfo();
					   druginfo.setMessage(value);   
					   druginfo.setDirectory(Long.parseLong(name.split("_")[1]));
					   
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
					drug.setImage(saveUrl+newFileName);
						File uploadedFile = new File(savePath, newFileName);
						try {
							item.write(uploadedFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
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
			List<Directory> list = directoryService.getAll();
			root.put("list", list);
			printFreemarker("default/add_drug.ftl", root);
			
		}
	}
	
	
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
			
			printFreemarker("default/show_drug.ftl", root);
		}
	}
	
	
	private DirectoryService directoryService = new DirectoryService();
	private DrugService drugService = new DrugService();
	private DrugInfoService drugInfoService = new DrugInfoService();
}
