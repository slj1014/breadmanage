package cn.edu.uts.web.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.uts.web.domain.AddRecord;
import cn.edu.uts.web.service.AddRecordServcie;

/**
 * 监听面包过期时候的监听器 并且网数据库中报表智能光插入数据
 * 
 * @author slj
 * 
 */
public class BreadDeadLineListener implements ServletContextListener {
	private Timer timer = new Timer();// 时间控制器

	private AddRecordServcie addRecordService;

	private ServletContext application;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("启动面包监听器");
		application = sce.getServletContext();
		// 得到service实例对象
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(application);// 在监听器的容器中查找Application容器
		addRecordService = (AddRecordServcie) ac
				.getBean("addRecordServiceImpl");
		// 指定明天时间的凌晨执行
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, 1);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		Date operTime = new Date(now.getTimeInMillis());
		timer.schedule(new MyTask(), operTime, 86400000);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 定义个触发器
	 * 
	 * @author slj
	 * 
	 */
	private class MyTask extends TimerTask {

		@Override
		public void run() {
           addRecordService.updateDieline();
		}

	}
}
