package cn.yi18.action;

import java.io.IOException;

import javax.servlet.ServletException;

import cn.yi18.lucene.DiseaseLucene;
import cn.yi18.lucene.DrugLucene;
import cn.yi18.lucene.LoreLucene;
import cn.yi18.lucene.NewsLucene;
import cn.yi18.lucene.PageUtil;
import cn.yi18.lucene.SearchFiles;
import cn.yi18.lucene.SymptomLucene;

public class SearchAction extends BaseAction 
{
	private static final int SIZE = 20;

	@Override
	public void execute() throws ServletException, IOException {
		
		String keyword = request.getParameter("q");
		int pagesize= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		
		keyword= new String(keyword.getBytes("iso-8859-1"), "UTF-8");//主要是处理get方式编码问题
		String type = request.getParameter("type");
		
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
		PageUtil page = searchFiles.query(keyword, pagesize, SIZE);
		root.put("page", page);
		root.put("keyword", keyword);
		root.put("type", type);
		printFreemarker("default/search.ftl", root);
	}
}
