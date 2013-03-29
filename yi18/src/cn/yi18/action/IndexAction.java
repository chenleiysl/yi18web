package cn.yi18.action;

import java.io.IOException;

import javax.servlet.ServletException;

import cn.yi18.pojo.POJO;
import cn.yi18.pojo.User;

public class IndexAction extends BaseAction
{
	@Override
	public void execute() throws ServletException, IOException {
		//User bean = new User();
		// User user = bean.get(1);
		printFreemarker("default/index.ftl", root);
		
	}
	
	public void index()
	{
		printFreemarker("default/index.ftl", root);
	}

}
