package cn.yi18.enums;

public class DirectoryEnum 
{
	
	/**
	 *  用户使用状态
	 * @author 陈磊
	 *
	 */
	public enum Search_Status
	{
			IsSearch(1),Search(0);//可用 ，不可用
		 	private int value;
		  private Search_Status(int value) {
			   this.value = value;
			}
		  public int getValue()
		  	{
			  return this.value;
		  	}
	}
	
	
	/**
	 * 目录的类型
	 * @author 陈磊
	 *
	 */
	public enum Type
	{
			Drug(1),Symptom(2),Disease(3);//
		 	private int value;
		  private Type(int value) {
			   this.value = value;
			}
		  public int getValue()
		  	{
			  return this.value;
		  	}
	}
	

	
	
}


