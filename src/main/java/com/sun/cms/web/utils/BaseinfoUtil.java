package com.sun.cms.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.sun.cms.web.dto.system.BaseInfo;

public class BaseinfoUtil {
	
	private static BaseinfoUtil baseinfoUtil = new BaseinfoUtil();
	
	private static Properties properties = new Properties();
	
	private BaseinfoUtil(){
		
	}
	
	public static BaseinfoUtil getInstance(){
		if (baseinfoUtil==null) {
			baseinfoUtil = new BaseinfoUtil();
		}
		try {
			String path = BaseinfoUtil.class.getClassLoader().getResource("baseinfo.properties").getPath();
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseinfoUtil;
	}
	
	public BaseInfo read(){
		BaseInfo baseInfo = new BaseInfo();
		baseInfo.setAddress(properties.getProperty("address"));
		baseInfo.setDomainName(properties.getProperty("domainName"));
		baseInfo.setEmail(properties.getProperty("email"));
		String indexPicSize = properties.getProperty("indexPicSize");
		String[] size = indexPicSize.split("\\*");
		baseInfo.setIndexPicWidth(Integer.parseInt(size[0]));
		baseInfo.setIndexPicHeight(Integer.parseInt(size[1]));
		baseInfo.setPhone(properties.getProperty("phone"));
		baseInfo.setRecordCode(properties.getProperty("recordCode"));
		baseInfo.setSitename(properties.getProperty("sitename"));
		baseInfo.setZipCode(properties.getProperty("zipCode"));
		return baseInfo;
	}
	
	public BaseInfo write(BaseInfo baseInfo){
		FileOutputStream fos = null;
		try {
			properties.setProperty("address", baseInfo.getAddress());
			properties.setProperty("domainName", baseInfo.getDomainName());
			properties.setProperty("email", baseInfo.getEmail());
			properties.setProperty("indexPicSize", baseInfo.getIndexPicWidth()+"*"+baseInfo.getIndexPicHeight());
			properties.setProperty("phone", baseInfo.getPhone());
			properties.setProperty("rocordCode", baseInfo.getRecordCode());
			properties.setProperty("sitename", baseInfo.getSitename());
			properties.setProperty("zipCode", baseInfo.getZipCode());
			String path = BaseinfoUtil.class.getClassLoader().getResource("baseinfo.properties").getPath();
			fos = new FileOutputStream(path);
			properties.store(fos, null);
			return baseInfo;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
