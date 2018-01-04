package com.sun.cms.web.dto.channel;

/**
 * 系统栏目树对象
 * 
 * @author Administrator
 *
 */
public class ChannelSimpleTree {

	private Integer id;

	private String name;
	
	private Integer pid;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}	

}
