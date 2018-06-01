package cn.edu.uts.web.util;


import org.hibernate.cfg.Configuration;
import org.junit.Test;
public class CreateTable {
		@Test
	public void crateTeble(){
		Configuration configuration=new Configuration().configure("/hibernate/hibernate.cfg.xml");//加载配置文件
		configuration.buildSessionFactory();//穿件sessionFactory
	}
}
