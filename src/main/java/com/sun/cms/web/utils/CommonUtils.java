package com.sun.cms.web.utils;

public class CommonUtils {
	/**
	 * 判断字符串是否为空 ：true表示不为空;false表示为空
	 * @author dongqun
	 * 2018年2月6日下午5:15:46
	 * @param content
	 * @return
	 */
	public static boolean isEmpty(String content){
		if (content!="" && content !=null) {
			return true;
		}else {
			return false;
		}
	}

}
