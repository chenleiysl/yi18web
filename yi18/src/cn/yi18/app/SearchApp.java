package cn.yi18.app;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.google.gson.Gson;

import cn.yi18.app.entity.AskApp;
import cn.yi18.lucene.DiseaseLucene;
import cn.yi18.lucene.DrugLucene;
import cn.yi18.lucene.LoreLucene;
import cn.yi18.lucene.NewsLucene;
import cn.yi18.lucene.PageInfo;
import cn.yi18.lucene.PageUtil;
import cn.yi18.lucene.SearchFiles;
import cn.yi18.lucene.SymptomLucene;

public class SearchApp extends BaseApp
{

	@Override
	public void execute() throws ServletException, IOException {
	
		 AskApp ask = getAskApp();
		 String type = ask.getType();
			
			SearchFiles searchFiles = null;
			if(type.equals("drug"))
			{
				searchFiles= new DrugLucene();
			}else if(type.equals("news"))
			{
				searchFiles=new NewsLucene();
			}
			else if(type.equals("lore"))
			{
				searchFiles=new LoreLucene();
			}else if(type.equals("symptom"))
			{
				searchFiles=new SymptomLucene();
			}
			else if(type.equals("disease"))
			{
				searchFiles=new DiseaseLucene();
			}
			String keyword= new String(ask.getKeyword().getBytes("iso-8859-1"), "UTF-8");//主要是处理get方式编码问题
			PageUtil page = searchFiles.query(keyword, ask.getPage(), ask.getLimit());
			
			int total=(int) page.getTotal();
			List<PageInfo> list = page.getList();
			Gson gson = new Gson();
			String json=gson.toJson(list);
			json=ask.getCallback()+"({\"success\": true,  \"total\":"+total+",\"yi18\":"+json+"})";
			printJson(json);
	}
	
}
