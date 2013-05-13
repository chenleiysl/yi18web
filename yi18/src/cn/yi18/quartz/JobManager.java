package cn.yi18.quartz;

import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import org.quartz.CalendarIntervalScheduleBuilder;
public class JobManager
{
	private static Logger _log = LoggerFactory.getLogger(JobManager.class);
	private static PropertiesConfiguration quartzProperties;
	private static Scheduler sched ;
	
	
	
	public void run() throws SchedulerException  
	{
		
		try {
			quartzProperties = new PropertiesConfiguration("quartz.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//创建一个简单的应用并且运行这个应用
		/**
		 * 一旦通过StdSchedulerFactory.getDefaultScheduler()获得一个scheduler ，那么应用将不会结束，
		 * 除非调用scheduler.shutdown()方法。
		 */
		sched = new StdSchedulerFactory().getScheduler();
		   _log.info("*********************启动应用*********************");
	        
		   _visitlogJob();//关键字过滤
		 
       
       sched.start();
       
        /**
         * 关闭应用
         */
      //  sched.shutdown();
	}
	
	
	public static void shutdown() throws SchedulerException 
	{
		if(sched!=null) 
		{
			sched.shutdown();
			 _log.info("*********************关闭应用*********************");
		}
	}
	
	
	/**
	 * 关键字过滤
	 * @throws SchedulerException
	 */
	private void _visitlogJob() throws SchedulerException 
	{
		
		JobDetail visitlogJob = newJob(VisitlogJob.class)//要被执行的Java类。
                .withIdentity("VisitlogJob")//名称
                .build();
        _log.info("*********************创建filterJob*********************");
    Date triggerStartTime = DateUtils.addMinutes(new Date(), quartzProperties.getInt("visitlogTrigger"));//xx分钟后执行
		Trigger visitlogTrigger = newTrigger()
        		.withIdentity("visitlogTrigger")
        		.withSchedule(simpleSchedule().
        				withIntervalInMinutes(quartzProperties.getInt("visitlogTrigger"))//设置循环时间
        				.repeatForever()//指定触发器将无限期地重复。
        				)//设置循环时间
        		
        		.startAt(triggerStartTime)
        		.build();
        
        sched.scheduleJob(visitlogJob, visitlogTrigger);
       _log.info(visitlogJob.getKey() + " " +
       					"作业 将开始运行 : " + triggerStartTime+" " +
       				"循环运行间隔："+((SimpleTrigger) visitlogTrigger).getRepeatInterval() / 1000 +"s   " +
       						"执行次数"+((SimpleTrigger) visitlogTrigger).getRepeatCount() ); 
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws SchedulerException {
		JobManager test = new JobManager();
		test.run();
	}
}
