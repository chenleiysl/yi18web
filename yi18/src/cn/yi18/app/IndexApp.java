package cn.yi18.app;

import java.io.IOException;

import javax.servlet.ServletException;

public class IndexApp extends BaseApp 
{
	@Override
	public void execute() throws ServletException, IOException {
		String url = request.basePath()+"yi18app/index.html";
		response.sendRedirect(url );
	}
}
