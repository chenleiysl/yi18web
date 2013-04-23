package cn.yi18.lucene;

public class DiseaseLucene extends LuceneManage implements IndexFiles,SearchFiles
{

	private static String path = "/lucene/disease";//药品存放路径
	public DiseaseLucene() {
		super(path);
	}
	
	public static void main(String[] args) {
		
		IndexFiles indexFiles = new DiseaseLucene();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setId(1);
		pageInfo.setContent("陈磊");
		pageInfo.setTitle("love");
		pageInfo.setUrl(" ");
		indexFiles.create(pageInfo);
		
		SearchFiles searchFiles = new DiseaseLucene();
		 PageUtil page = searchFiles.query("陈磊",1,20);
		System.out.println(page.getTotal());
	}
}
