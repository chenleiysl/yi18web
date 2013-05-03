package cn.yi18.action;

import java.io.IOException;

import javax.servlet.ServletException;

import cn.yi18.lucene.DrugLucene;
import cn.yi18.lucene.PageUtil;
import cn.yi18.lucene.SearchFiles;

public class SearchAction extends BaseAction 
{
	private static final int SIZE = 20;

	@Override
	public void execute() throws ServletException, IOException {
		
		String keyword = request.getParameter("q");
		int pagesize= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		
		keyword= new String(keyword.getBytes("iso-8859-1"), "UTF-8");//主要是处理get方式编码问题
		
		
		//System.out.println(keyword);
		
		SearchFiles searchFiles = new DrugLucene();
		PageUtil page = searchFiles.query(keyword, pagesize, SIZE);
		root.put("page", page);
		root.put("keyword", keyword);
		printFreemarker("default/search.ftl", root);
	}
}
