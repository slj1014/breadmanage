package cn.edu.uts.web.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringUtil {
	public static ApplicationContext context;
	static{
		context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	}
}
