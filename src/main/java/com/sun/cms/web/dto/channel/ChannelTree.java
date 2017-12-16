package com.sun.cms.web.dto.channel;

import java.util.List;

/**
 * 系统栏目树对象
 * 
 * @author Administrator
 *
 */
public class ChannelTree {

	private Integer id;

	private String name;
	
	private Integer pid;
	
	private boolean open;
	
	private List<ChannelTree> children;

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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	
	public List<ChannelTree> getChildren() {
		return children;
	}

	public void setChildren(List<ChannelTree> children) {
		this.children = children;
	}

	public ChannelTree() {
	}

	public ChannelTree(Integer id, String name, Integer pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}

	

}
