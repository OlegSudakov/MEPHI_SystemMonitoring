package com.sysmon.core.server.metric.saver;

import com.sysmon.core.server.measure.Measure;
import com.sysmon.core.server.metric.MeasureResponse;
import com.sysmon.core.server.metric.Metric;
import com.sysmon.core.server.metric.CommonMetricManager;
import com.sysmon.core.server.metric.MetricRequest;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.sysmon.core.server.metric.saver.MetricSaverJobDataMapParam.METRIC;
import static com.sysmon.core.server.metric.saver.MetricSaverJobDataMapParam.METRIC_MANAGER;
import static com.sysmon.core.server.metric.saver.MetricSaverJobDataMapParam.MEASURE_SAVER;


public class MetricSaverJob implements Job
{
    private static final Logger log = Logger.getLogger(MetricSaverJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Metric collectibleMetric = (Metric) jobDataMap.get(METRIC.name());
        MeasureSaver measureSaver = (MeasureSaver) jobDataMap.get(MEASURE_SAVER.name());
        CommonMetricManager commonMetricManager = (CommonMetricManager) jobDataMap.get(METRIC_MANAGER.name());

        CompletableFuture<Optional<MeasureResponse>> measureCollectionOptionalCompletableFuture = commonMetricManager.handle(new MetricRequest<>(Collections.singleton(collectibleMetric)));
        try {
            Optional<MeasureResponse> measureCollectionOptional = measureCollectionOptionalCompletableFuture.get();
            if (measureCollectionOptional.isPresent()) {
                Collection<Measure> measures = measureCollectionOptional.get().getMeasures();
                measureSaver.save(measures);
            } else {
                log.error(String.format("Cannot obtain measure for metric %s", collectibleMetric));
            }
        } catch (Exception e) {
            log.error(String.format("Can not obtain measure for metric %s", collectibleMetric), e);
        }
    }
}
