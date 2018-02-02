package com.sun.cms.web.auth;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 权限分配工具类
 * @author dongqun
 * 2018年1月31日下午1:21:25
 */
public class AuthUtil {
	/**
	 * 初始化各类角色能访问的接口 
	 * 以类名.函数名的形式存储
	 * @author dongqun
	 * 2018年1月31日下午2:01:41
	 * @param packageName
	 * @return
	 */
	public static Map<String, Set<String>> initAuth(String packageName){	
		Map<String, Set<String>> auths = new HashMap<>();
		try {
			List<String> classes = getClassByPackage(packageName);
			for (String c : classes) {
				String name = packageName+"."+c.substring(0, c.lastIndexOf(".class"));
				Class<?> cls =  Class.forName(name);
				if (!cls.isAnnotationPresent(AuthClass.class)) continue;
				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
					if (!method.isAnnotationPresent(AuthMethod.class)) {
						continue;
					}
					AuthMethod authMethod = method.getAnnotation(AuthMethod.class);
					String role = authMethod.role();
					Set<String> auth = auths.get(role);
					if (auth==null) {
						auth = new HashSet<>();
						auths.put(role, auth);
					}
					auth.add(name+"."+method.getName());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auths;
	}
	
	private static List<String> getClassByPackage(String packageName){
		List<String> result = new ArrayList<>();
		String pname = packageName.replace(".", "/");
		String path = AuthUtil.class.getClassLoader().getResource(pname).getPath();
		File file = new File(path);
		String[] files = file.list();
		for (String name : files) {
			String subPath = path + "/" + name;
			File subFile = new File(subPath);
			if (subFile.isDirectory()) {
				String[] files1 = subFile.list();
				for (String name1 : files1) {
					String subPath1 = path + "/" + name + "/" +name1;
					File subFile1 = new File(subPath1);
					if (subFile1.getName().endsWith(".class")) {
						String name2 = name + "." + name1;
						result.add(name2);
					}
				}
				
			}else{
				if (subFile.getName().endsWith(".class")) {
					result.add(name);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(initAuth("com.sun.cms.web.controller"));
	}

	

}
