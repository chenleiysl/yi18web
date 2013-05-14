package cn.yi18.lucene;

import org.apache.commons.lang3.SystemUtils;

public class DrugLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = SystemUtils.USER_DIR+"/lucene/drug";//药品存放路径
	public DrugLucene() {
		super(path);
	}
	
	
}
