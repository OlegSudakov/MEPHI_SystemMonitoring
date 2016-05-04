package com.sysmon.core.server.metric.saver;

import com.sysmon.core.server.metric.CommonMetricManager;
import com.sysmon.core.server.metric.Metric;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MetricSaverImpl implements MetricSaver, Ordered
{
    private static final Logger log = Logger.getLogger(MetricSaverImpl.class);

    private ApplicationContext context;

    private MeasureSaver measureSaver;

    private Scheduler scheduler;

    @Autowired
    private MetricSaverImpl(
            ApplicationContext context,
            MeasureSaver measureSaver
    )
    {
        this.context = context;
        this.measureSaver = measureSaver;
    }

    @Override
    public int getOrder()
    {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void add(Metric metric)
    {
        Trigger trigger = createTrigger(metric);
        try {
            if (scheduler.getTrigger(trigger.getKey()) != null) {
                scheduler.rescheduleJob(trigger.getKey(), trigger);
            } else {
                JobDetail job = createJob(metric);
                scheduler.scheduleJob(job, trigger);
            }
        } catch (SchedulerException e) {
            log.error("Can not add metric for scheduling");
        }
    }

    @Override
    public void remove(Metric metric)
    {
        Trigger trigger = createTrigger(metric);
        try {
            scheduler.unscheduleJob(trigger.getKey());
        } catch (SchedulerException e) {
            log.error("Can not unschedule metric saving");
        }
    }

    @PostConstruct
    private void init() throws SchedulerException
    {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
    }

    private static final String METRIC_SAVER_JOB_NAME_PREFIX = "MetricSaverJob_";
    private static final String METRIC_SAVER_TRINGER_NAME_PREFIX = "MetricSaverTrigger_";
    private static final String METRIC_SAVER_GROUP = "MetricSaverGroup";

    private JobDetail createJob(Metric metric)
    {
        JobDetail job = JobBuilder.newJob(MetricSaverJob.class).withIdentity(METRIC_SAVER_JOB_NAME_PREFIX + metric.getId()).build();
        job.getJobDataMap().put(MetricSaverJobDataMapParam.METRIC.name(), metric);
        job.getJobDataMap().put(MetricSaverJobDataMapParam.MEASURE_SAVER.name(), context.getBean(MeasureSaver.class));
        job.getJobDataMap().put(MetricSaverJobDataMapParam.METRIC_MANAGER.name(), context.getBean(CommonMetricManager.class));
        return job;
    }

    private Trigger createTrigger(Metric metric)
    {
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(metric.getCronString()))
                .withIdentity(METRIC_SAVER_TRINGER_NAME_PREFIX + metric.getId(), METRIC_SAVER_GROUP)
                .build();
    }
}
