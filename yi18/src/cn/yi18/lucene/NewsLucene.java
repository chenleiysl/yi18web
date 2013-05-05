package cn.yi18.lucene;

public class NewsLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = "/lucene/news";//新闻存放路径
	public NewsLucene() {
		super(path);
	}
	
	
}
