package com.sun.cms.web.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解用于对某一方法（接口）进行权限控制
 * @author dongqun
 * 2018年1月31日上午11:17:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMethod {
	/**
	 * 如果role为base则所有登陆用户都可以访问
	 * 如果role为publisher则只有文章发布人员可以访问
	 * 如果role为checker则只有文章审核人员可以访问
	 * 如果接口上没有此注解则只有管理员可以访问
	 * @author dongqun
	 * 2018年1月31日上午11:15:01
	 * @return
	 */
	String role() default "base";
}
