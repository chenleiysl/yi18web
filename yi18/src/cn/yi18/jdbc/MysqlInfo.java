package cn.yi18.jdbc;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class MysqlInfo
{
	private String name;
	private String user;
	private String username;
	private String host;
	private String hostname;
	private String port;
	private String password;
	
	public MysqlInfo() {
		
		String json = System.getenv("VCAP_SERVICES");
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement =jsonParser.parse(json);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("mysql-5.1");
		JsonObject jsonO = jsonArray.get(0).getAsJsonObject().getAsJsonObject("credentials");
		//Gson gson = new Gson();
		//Mysql mysql = gson.fromJson(jsonO, Mysql.class);
		this.name = jsonO.get("name").toString().replace("\"","");
		this.user = jsonO.get("user").toString().replace("\"","");
		this.username = jsonO.get("username").toString().replace("\"","");
		this.host = jsonO.get("host").toString().replace("\"","");
		this.hostname = jsonO.get("hostname").toString().replace("\"","");
		this.port = jsonO.get("port").toString().replace("\"","");
		this.password = jsonO.get("password").toString().replace("\"","");
		
		

	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	

}
