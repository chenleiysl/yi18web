package cn.yi18.pojo;

/**
 * 药品内容
 * @author 陈磊
 *
 */
public class Druginfo extends POJO
{
	protected Long drug;//药品编号
	protected Long directory;//药品目录编号
	protected String message;//内容
	public Long getDrug() {
		return drug;
	}
	public void setDrug(Long drug) {
		this.drug = drug;
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
