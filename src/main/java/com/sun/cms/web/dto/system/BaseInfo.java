package com.sun.cms.web.dto.system;

/**
 * 网站基本信息
 * @author dongqun
 * 2018年2月26日下午2:54:14
 */
public class BaseInfo {
	private String sitename;
	private String	address;
	private String	zipCode;
	private String	recordCode;
	private	String phone;
	private	String email;
	private	int indexPicWidth;
	private int indexPicHeight;
	private	String domainName;
	
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getRecordCode() {
		return recordCode;
	}
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIndexPicWidth() {
		return indexPicWidth;
	}
	public void setIndexPicWidth(int indexPicWidth) {
		this.indexPicWidth = indexPicWidth;
	}
	public int getIndexPicHeight() {
		return indexPicHeight;
	}
	public void setIndexPicHeight(int indexPicHeight) {
		this.indexPicHeight = indexPicHeight;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
