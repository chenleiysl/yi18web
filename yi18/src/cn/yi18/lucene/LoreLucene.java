package cn.yi18.lucene;

public class LoreLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = "/lucene/lore";//健康知识存放路径
	public LoreLucene() {
		super(path);
	}
	
	
}
