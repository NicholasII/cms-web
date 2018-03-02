package com.sun.cms.web.constant;
/**
 * 系统角色
 * @author dongqun
 * 2018年3月2日下午2:01:46
 */
public enum RoleType {
	
	ADMIN("admin"),CHECKER("articlechecker"),PUBLISHER("articlepublisher"),USER("user"),BASE("base");
	
	private String type;

	private RoleType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
