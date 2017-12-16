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
	private int p_id;
	/**
	 * 栏目的名称
	 */
	private String name;
	/**
	 * 栏目是否是自定义链接，0表示否，1表示是
	 */
	private int customLink;
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
	private int isIndex;
	/**
	 * 是否是首页的顶部导航栏目，0表示否，1表示是
	 */
	private int isTopNav;
	/**
	 * 是否是推荐栏目，0表示否，1表示是
	 */
	private int recommend;
	/**
	 * 栏目的状态，0表示启用，1表示停止
	 */
	private int status;
	/**
	 * 栏目的序号
	 */
	private int orders;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
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

	public int getCustomLink() {
		return customLink;
	}

	public void setCustomLink(int customLink) {
		this.customLink = customLink;
	}

	public String getCustomLinkUrl() {
		return customLinkUrl;
	}

	public void setCustomLinkUrl(String customLinkUrl) {
		this.customLinkUrl = customLinkUrl;
	}

	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}

	public int getIsTopNav() {
		return isTopNav;
	}

	public void setIsTopNav(int isTopNav) {
		this.isTopNav = isTopNav;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

}
