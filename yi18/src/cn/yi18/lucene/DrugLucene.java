package cn.yi18.lucene;

public class DrugLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = "/lucene/drug";//药品存放路径
	public DrugLucene() {
		super(path);
	}
	
	
}
