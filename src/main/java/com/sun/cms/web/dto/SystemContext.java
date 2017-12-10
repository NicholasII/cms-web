package com.sun.cms.web.dto;

public class SystemContext {

	/**
	 * 分页大小
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/**
	 * 分页的起始页
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	/**
	 * 列表的排序字段
	 */
	//private static ThreadLocal<String> sort = new ThreadLocal<String>();
	/**
	 * 列表的排序方式
	 */
	//private static ThreadLocal<String> order = new ThreadLocal<String>();
	
	
	public static Integer getPageSize() {
		return pageSize.get();
	}
	
	public static void setPageSize(Integer _pageSize) {
		pageSize.set(_pageSize);
	}
	
	public static Integer getPageOffset() {
		return pageOffset.get();
	}
	
	public static void setPageOffset(Integer _pageOffset) {
		pageOffset.set(_pageOffset);;
	}
	
	public static void removePageSize() {
		pageSize.remove();
	}
	
	public static void removePageOffset() {
		pageOffset.remove();
	}
	
}
