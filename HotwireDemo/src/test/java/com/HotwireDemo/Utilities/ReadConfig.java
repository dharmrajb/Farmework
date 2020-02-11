package com.HotwireDemo.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {

		File src = new File("./Configuration/Config.properties");
		try {
			
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
			
		} catch (Exception e) {

			System.out.println("Exception Message " + e.getMessage());
		}

	}
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseurl");
		return url;
	}
	
	public String getChromePath()
	{
		String chrome=pro.getProperty("chromePath");
		return chrome;
	}
	public String getFirefoxPath()
	{
		String firefox=pro.getProperty("FirefoxPath");
		return firefox;
	}
	public String getInternetExplorerPath()
	{
		String ie=pro.getProperty("InternetExplorerPath");
		return ie;
	}
	
}
