package cn.yi18.pojo;

/**
 *  疾病内容
 * @author 陈磊
 *
 */
public class Diseaseinfo extends POJO
{
	protected Long disease;//药品编号
	protected Long directory;//药品目录编号
	protected String message;//内容
	
	
	
	public Long getDisease() {
		return disease;
	}
	public void setDisease(Long disease) {
		this.disease = disease;
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
