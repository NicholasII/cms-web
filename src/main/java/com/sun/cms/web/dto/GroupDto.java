package com.sun.cms.web.dto;

import java.util.Date;

import com.sun.cms.model.BaseDto;

public class GroupDto extends BaseDto{
	
    private String groupId;

    private String groupName;

    private String groupNote;

    private Date addTime;

    private Date updateTime;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupNote() {
        return groupNote;
    }

    public void setGroupNote(String groupNote) {
        this.groupNote = groupNote == null ? null : groupNote.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}