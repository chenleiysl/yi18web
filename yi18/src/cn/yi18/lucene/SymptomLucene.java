package cn.yi18.lucene;

import org.apache.commons.lang3.SystemUtils;

public class SymptomLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = SystemUtils.USER_DIR+"/lucene/symptom";//病状存放路径
	public SymptomLucene() {
		super(path);
	}
	
	
	public static void main(String[] args) {
		System.out.println(SystemUtils.USER_DIR);
		System.out.println(System.getProperty("user.dir"));
	}
}
