package cn.yi18.enums;

public class DiseaseEnum 
{
	public enum Check_Status
	{
			IsCheck(1),NoCheck(0);
		 	private int value;
		  private Check_Status(int value) {
			   this.value = value;
			}
		  public int getValue()
		  	{
			  return this.value;
		  	}
	}
}
