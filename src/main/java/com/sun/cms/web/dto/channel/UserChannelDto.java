package com.sun.cms.web.dto.channel;

import com.sun.cms.model.BaseDto;
/**
 * 用户管理的栏目表
 * @author dongqun
 * 2018年1月12日下午5:05:56
 */
public class UserChannelDto extends BaseDto {

	/**
	 * @author dongqun
	 * 2018年1月12日下午5:05:38
	 */
	private static final long serialVersionUID = 1L;

	private Integer channelid;
	
	private String userid;
	
	private Integer channelpid;
	
	private String channelname;

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
