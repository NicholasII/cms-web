package com.sun.cms.web.dto.channel;

import com.sun.cms.model.BaseDto;

public class Channel extends BaseDto{

	/**
	 * 栏目的主键
	 */
	private Integer id;
	/**
	 * 父栏目
	 */
	private Integer pid;
	/**
	 * 栏目的名称
	 */
	private String name;
	/**
	 * 栏目是否是自定义链接，0表示否，1表示是
	 */
	private Integer customLink;
	/**
	 * 自定义链接的地址
	 */
	private String customLinkUrl;
	/**
	 * 栏目的类型
	 */
	private String channelType;
	/**
	 * 是否是首页栏目，0表示否，1表示是
	 */
	private Integer isIndex;
	/**
	 * 是否是首页的顶部导航栏目，0表示否，1表示是
	 */
	private Integer isTopNav;
	/**
	 * 是否是推荐栏目，0表示否，1表示是
	 */
	private Integer recommend;
	/**
	 * 栏目的状态，0表示启用，1表示停止
	 */
	private Integer status;
	/**
	 * 栏目的序号
	 */
	private Integer orders;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomLink() {
		return customLink;
	}

	public void setCustomLink(Integer customLink) {
		this.customLink = customLink;
	}

	public String getCustomLinkUrl() {
		return customLinkUrl;
	}

	public void setCustomLinkUrl(String customLinkUrl) {
		this.customLinkUrl = customLinkUrl;
	}

	public Integer getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(Integer isIndex) {
		this.isIndex = isIndex;
	}

	public Integer getIsTopNav() {
		return isTopNav;
	}

	public void setIsTopNav(Integer isTopNav) {
		this.isTopNav = isTopNav;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

}
