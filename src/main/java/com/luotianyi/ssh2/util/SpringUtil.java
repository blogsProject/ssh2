package com.luotianyi.ssh2.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext arg0;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		if (arg0 != null) {
			SpringUtil.arg0 = arg0;
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("-----ApplicationContext配置成功");
			System.out.println("-----------------------------------------------------------------------------------");
		}
	}

	public static ApplicationContext getApplicationContext() {
		return arg0;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
}
