package com.sysmon.core.server.metric.saver;

import com.sysmon.core.server.utils.MeasureToMeasureEntityConverter;
import com.sysmon.core.dal.dao.MeasureDao;
import com.sysmon.core.dal.entity.measure.MeasureEntity;
import com.sysmon.core.server.measure.Measure;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Component
public class MeasureSaverImpl implements MeasureSaver
{
    private static Logger log = Logger.getLogger(MeasureSaverImpl.class);

    private BlockingQueue<Measure> measureBlockingQueue = new LinkedBlockingQueue<>();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Integer batchSize;

    private MeasureDao measureDao;

    private MeasureToMeasureEntityConverter measureToMeasureEntityConverter;

    @Autowired
    public MeasureSaverImpl(
            @Value("${metricsaver.batchsize}") Integer batchSize,
            MeasureDao measureDao,
            MeasureToMeasureEntityConverter measureToMeasureEntityConverter
    )
    {
        this.batchSize = batchSize;
        this.measureDao = measureDao;
        this.measureToMeasureEntityConverter = measureToMeasureEntityConverter;
    }

    @PostConstruct
    private void init()
    {
        executorService.submit(new SavingTask(batchSize, measureDao, measureToMeasureEntityConverter));
    }

    @Override
    public void save(Measure measure)
    {
        try {
            measureBlockingQueue.put(measure);
        } catch (InterruptedException e) {
            log.error("Error while waiting for putting in queue", e);
        }
    }

    @Override
    public void save(Collection<Measure> measures)
    {
        for (Measure measure : measures) {
            try {
                measureBlockingQueue.put(measure);
            } catch (InterruptedException e) {
                log.error("Error while waiting for putting in queue", e);
            }
        }
    }

    private class SavingTask implements Runnable
    {
        private final Integer batchSize;
        private final Collection<Measure> measures;
        private final Collection<MeasureEntity> measureEntities;
        private final MeasureDao measureDao;
        private final MeasureToMeasureEntityConverter measureToMeasureEntityConverter;

        public SavingTask(
                Integer batchSize,
                MeasureDao measureDao,
                MeasureToMeasureEntityConverter measureToMeasureEntityConverter
        )
        {
            this.batchSize = batchSize;
            this.measureDao = measureDao;
            this.measureToMeasureEntityConverter = measureToMeasureEntityConverter;
            measures = new ArrayList<>(batchSize);
            measureEntities = new ArrayList<>(batchSize);
        }

        @Override
        public void run()
        {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    measures.add(measureBlockingQueue.take());
                    measureBlockingQueue.drainTo(measures, batchSize - 1);
                    measureEntities.addAll(measures.stream()
                            .parallel()
                            .map(measureToMeasureEntityConverter)
                            .collect(Collectors.toSet())
                    );

                    measureDao.save(measureEntities);
                } catch (InterruptedException e) {
                    log.error("Interrupted while waiting for measure from queue", e);
                } catch (Exception e) {
                    log.error("Error while saving measures", e);
                } finally {
                    measures.clear();
                    measureEntities.clear();
                }
            }
        }
    }
}
