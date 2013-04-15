package cn.yi18.pojo;

/**
 * 药品内容
 * @author 陈磊
 *
 */
public class Symptominfo extends POJO
{
	protected Long symptoms;//病状编号
	protected Long directory;//病状目录编号
	protected String message;//内容
	
	
	public Long getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(Long symptoms) {
		this.symptoms = symptoms;
	}
	public Long getDirectory() {
		return directory;
	}
	public void setDirectory(Long directory) {
		this.directory = directory;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
