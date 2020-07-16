package com.maven.cms.ClinicalManagementSystem.Config;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	FileInputStream ip;
	Properties prop;
	String confurl;
	String confusername;
	String confpassword;
	String confpath;
//Read Property File
	public ReadConfig() {
		try {
			ip = new FileInputStream("./src/main/java/com/maven/cms/ClinicalManagementSystem/Config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//get URL
	public String getUrl() {
		confurl = prop.getProperty("url");
		return confurl;
	}
//get user name
	public String getUsername() {
		confusername = prop.getProperty("username");
		return confusername;
	}
//get password
	public String getPassword() {
		confpassword = prop.getProperty("password");
		return confpassword;
	}
//get driver path
	public String getChromepath() {
		confpath = prop.getProperty("chromepath");
		return confpath;
	}
}

