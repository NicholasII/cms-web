package com.sun.cms.web.dto.channel;

import com.sun.cms.model.BaseDto;

/**
 * 分组-栏目对应关系表
 * 
 * @author dongqun 2017年12月26日上午10:11:54
 */
public class GroupChannelDto extends BaseDto {
	
	private Integer channelid;
	private String groupid;
	private Integer channelpid;
	private String channelname;

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public Integer getChannelpid() {
		return channelpid;
	}

	public void setChannelpid(Integer channelpid) {
		this.channelpid = channelpid;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
}
