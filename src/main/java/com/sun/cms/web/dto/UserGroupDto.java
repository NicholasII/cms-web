package com.sun.cms.web.dto;

import com.sun.cms.model.BaseDto;

public class UserGroupDto extends BaseDto{

	private Integer id;
	
    private String userid;

    private String groupid;
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
    }
}