package cn.yi18.lucene;

import org.apache.commons.lang3.SystemUtils;

public class LoreLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = SystemUtils.USER_DIR+"/lucene/lore";//健康知识存放路径
	public LoreLucene() {
		super(path);
	}
	
	
}
