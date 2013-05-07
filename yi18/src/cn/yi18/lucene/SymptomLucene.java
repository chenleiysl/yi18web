package cn.yi18.lucene;

public class SymptomLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = "/lucene/symptom";//病状存放路径
	public SymptomLucene() {
		super(path);
	}
	
	
}
