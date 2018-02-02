package com.sun.cms.web.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 此注解用于对类进行权限控制
 * @author dongqun
 * 2018年1月31日上午11:10:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthClass {
	/**
	 * 如果value为admin则，该类下的接口只有管理员能够访问
	 * 如果value为login则，该类下的接口为类中方法注释中指定的角色访问
	 * @author dongqun
	 * 2018年1月31日上午11:11:41
	 * @return
	 */
	String value() default "admin";
}
