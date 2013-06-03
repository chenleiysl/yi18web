package cn.yi18.lucene;

import java.util.List;

import org.apache.commons.lang3.SystemUtils;

public class NewsLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = SystemUtils.USER_DIR+"/lucene/news";//新闻存放路径
	public NewsLucene() {
		super(path);
	}
	
	
	
}
