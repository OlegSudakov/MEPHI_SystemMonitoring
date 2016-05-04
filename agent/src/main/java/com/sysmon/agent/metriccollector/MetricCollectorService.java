package com.sysmon.agent.metriccollector;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;
import com.sysmon.agent.commandexecutor.api.CommandExecutor;
import com.sysmon.agent.measure.Measure;
import com.sysmon.agent.metriccollector.collectors.creators.MeasureCreator;
import com.sysmon.agent.metriccollector.collectors.creators.MetricCollectorCommandCreator;
import com.sysmon.agent.metriccollector.collectors.api.MetricCollectorTask;
import com.sysmon.agent.metriccollector.collectors.creators.MetricParserCreator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.javatuples.Pair;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class MetricCollectorService implements Function<MetricCollectorTask, CompletableFuture<Measure>>
{
    private static final Logger log = Logger.getLogger(MetricCollectorService.class);

    @Autowired
    private MetricCollectorCommandCreator metricCollectorCommandCreator;

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private MetricParserCreator metricParserCreator;

    @Autowired
    private MeasureCreator measureCreator;

    @Override
    public CompletableFuture<Measure> apply(MetricCollectorTask metricCollectorTask)
    {
        CompletableFuture<CommandExecutionResult> executionResultPromise = CompletableFuture
                .supplyAsync(() -> metricCollectorCommandCreator.apply(metricCollectorTask))
                .thenApplyAsync(command -> commandExecutor.apply(command));

        CompletableFuture<Object> measureParserPromise = CompletableFuture
                .supplyAsync(() -> metricParserCreator.apply(metricCollectorTask))
                .thenApplyAsync(parser -> parser.apply(executionResultPromise.join()));

        CompletableFuture<Measure> promise = CompletableFuture
                .supplyAsync(() -> Pair.with(executionResultPromise.join().executionDateTime().getTime() / 1000, measureParserPromise.join()))
                .thenApplyAsync(pair -> measureCreator.apply(pair))
                .exceptionally(t -> {
                    log.error(String.format("Cannot get proceed metric collector task %s", metricCollectorTask), t);
                    return null;
                });

        return promise;
    }
}
