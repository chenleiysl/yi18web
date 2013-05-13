package cn.yi18.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yi18.quartz.JobManager;



/**
 * Web应用的监听 可用于初始化 和 关闭
 * @author 陈磊
 * @since 2012/8/10
 *
 */
@WebListener
public class SCListener implements ServletContextListener
{

	private static Logger _log = LoggerFactory.getLogger(SCListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent event) 
	{
		try {
			JobManager.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		_log.info("正常启动ServletContextListener监听器");
		JobManager jobManager = new JobManager();
		try {
			jobManager.run();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
