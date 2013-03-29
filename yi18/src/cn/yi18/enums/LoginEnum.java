package cn.yi18.enums;

public class LoginEnum
{
	/**
	 *  用户是否登录
	 * @author 陈磊
	 *
	 */
	public enum Online_Status
	{
			IsOnline(1),NoOnline(0);
		 	private int value;
		  private Online_Status(int value) {
			   this.value = value;
			}
		  public int getValue()
		  	{
			  return this.value;
		  	}
	}
	
	/**
	 *  用户是否自动登录
	 * @author 陈磊
	 *
	 */
	public enum Auto_Status
	{
			IsAuto(1),NoAuto(0);
		 	private int value;
		  private Auto_Status(int value) {
			   this.value = value;
			}
		  public int getValue()
		  	{
			  return this.value;
		  	}
	}
	
	
}
