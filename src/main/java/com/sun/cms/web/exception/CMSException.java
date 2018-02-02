package com.sun.cms.web.exception;

public class CMSException extends RuntimeException {

	/**
	 * @author dongqun
	 * 2018年1月31日下午4:29:08
	 */
	private static final long serialVersionUID = 1L;

	public CMSException() {
		super();
	}

	public CMSException(String message, Throwable cause) {
		super(message, cause);
	}

	public CMSException(String message) {
		super(message);
	}

	public CMSException(Throwable cause) {
		super(cause);
	}
	
	

}
