package com.datadrizzle.scheduler;

import java.text.ParseException;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTutorial {
	
	public static void main(String[] args) throws ParseException, SchedulerException {
		System.out.println("starting a scheduler");
		 // specify the job' s details..
        JobDetail job = JobBuilder.newJob()
        						  .ofType(TestJob.class)
                                  .withIdentity("testJob")
                                  .build();

        // specify the running period of the job
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                                           .withIntervalInSeconds(5)
                                                                           .repeatForever())
                                         .build();

        //schedule the job
        SchedulerFactory schFactory = new StdSchedulerFactory();
        Scheduler sch = schFactory.getScheduler();
        sch.start();
        sch.scheduleJob(job, trigger);
        System.out.println("scheduler started");

	}

}
