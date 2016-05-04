package com.sysmon.agent.metriccollector.collectors.memory.free;

import com.sysmon.agent.commandexecutor.api.CommandExecutionResult;
import com.sysmon.agent.metriccollector.collectors.api.MetricCollector;
import com.google.common.primitives.Longs;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MemoryFreeMetricCollector implements MetricCollector<MemoryFreeMeasureValue>
{
    private static final Logger log = Logger.getLogger(MemoryFreeMetricCollector.class);
    private static final String COLUMN_SPLITTER = "\\s+";

    private MemoryFreeMetricCollectorTask memoryFreeMetricCollectorTask;

    public MemoryFreeMetricCollector(MemoryFreeMetricCollectorTask memoryFreeMetricCollectorTask)
    {
        this.memoryFreeMetricCollectorTask = memoryFreeMetricCollectorTask;
    }

    @FunctionalInterface
    private interface StringToStringListConverter
    {
        List<String> apply(
                String str,
                int startIndex,
                int endIndex,
                String splitter
        );
    }

    private MemoryFreeMeasureValue parse(String stringToParse)
    {
        int headerEndIndex = stringToParse.indexOf('\n');
        int memStartIndex = stringToParse.indexOf(MemoryFreeRowType.MEM.getRowBeginning());
        int memEndIndex = stringToParse.indexOf('\n', memStartIndex);

        int swapStartIndex = stringToParse.indexOf(MemoryFreeRowType.SWAP.getRowBeginning());
        int endEndIndex = stringToParse.indexOf('\n', swapStartIndex);

        StringToStringListConverter converter = (str, startIndex, endIndex, splitter) -> Arrays.asList(str.substring(startIndex, endIndex).split(splitter));

        Function<List<String>, List<String>> removeEmptyValues = (list) ->
                list.stream()
                        .filter(str -> !str.isEmpty())
                        .collect(Collectors.toList());

        List<String> headerList = removeEmptyValues.apply(converter.apply(stringToParse, 0, headerEndIndex, COLUMN_SPLITTER));
        List<String> memList = removeEmptyValues.apply(converter.apply(stringToParse, memStartIndex + MemoryFreeRowType.MEM.getRowBeginning().length(), memEndIndex, COLUMN_SPLITTER));
        List<String> swapList = removeEmptyValues.apply(converter.apply(stringToParse, swapStartIndex + MemoryFreeRowType.SWAP.getRowBeginning().length(), endEndIndex, COLUMN_SPLITTER));

        Map<MemoryFreeColumnType, Integer> memoryFreeColumnTypeIntegerMap = Arrays.asList(MemoryFreeColumnType.values()).stream().collect(Collectors.toMap(
                memoryFreeColumnType -> memoryFreeColumnType,
                memoryFreeColumnType -> headerList.indexOf(memoryFreeColumnType.getColumnName())
        ));

        Function<MemoryFreeType, Long> valueParser = (memoryFreeType) -> {
            int columnIndexOfStringToParse = memoryFreeColumnTypeIntegerMap.get(memoryFreeType.getMemoryFreeColumnType());
            if (columnIndexOfStringToParse == -1) {
                log.error(String.format("Cannot find header %s", headerList));
                return null;
            }
            String strToParse = null;
            switch (memoryFreeType.getMemoryFreeRowType()) {
                case MEM:
                    strToParse = memList.get(columnIndexOfStringToParse);
                    break;
                case SWAP:
                    strToParse = swapList.get(columnIndexOfStringToParse);
                    break;
            }
            strToParse = strToParse.replace(",", ".");
            Long value = Longs.tryParse(strToParse);
            if (value == null) {
                log.error(String.format("%s cannot be parsed for metric type %s %s", strToParse, memoryFreeType.getMemoryFreeColumnType(), memoryFreeType.getMemoryFreeRowType()));
            }
            return value;
        };

        Long totalMemory = null;
        Long freeMemory = null;
        Long totalSwap = null;
        Long freeSwap = null;
        if (memoryFreeMetricCollectorTask.isTotalMemoryEnabled()) {
            totalMemory = valueParser.apply(MemoryFreeType.TOTAL_MEMORY);
        }
        if (memoryFreeMetricCollectorTask.isFreeMemoryEnabled()) {
            freeMemory = valueParser.apply(MemoryFreeType.FREE_MEMORY);
        }
        if (memoryFreeMetricCollectorTask.isTotalSwapEnabled()) {
            totalSwap = valueParser.apply(MemoryFreeType.TOTAL_SWAP);
        }
        if (memoryFreeMetricCollectorTask.isFreeSwapEnabled()) {
            freeSwap = valueParser.apply(MemoryFreeType.FREE_SWAP);
        }
        return new MemoryFreeMeasureValue(totalMemory, freeMemory, totalSwap, freeSwap);
    }

    @Override
    public MemoryFreeMeasureValue apply(CommandExecutionResult commandExecutionResult)
    {
        return parse(commandExecutionResult.getOutput());
    }

    enum MemoryFreeRowType
    {
        MEM("Mem:"),
        SWAP("Swap:");

        private String rowBeginning;

        MemoryFreeRowType(String rowBeginning)
        {
            this.rowBeginning = rowBeginning;
        }

        public String getRowBeginning()
        {
            return rowBeginning;
        }
    }

    enum MemoryFreeColumnType
    {
        TOTAL("total"),
        FREE("free");

        private String columnName;

        MemoryFreeColumnType(String columnName)
        {
            this.columnName = columnName;
        }

        public String getColumnName()
        {
            return columnName;
        }
    }

    enum MemoryFreeType
    {
        TOTAL_MEMORY(MemoryFreeColumnType.TOTAL, MemoryFreeRowType.MEM),
        FREE_MEMORY(MemoryFreeColumnType.FREE, MemoryFreeRowType.MEM),
        TOTAL_SWAP(MemoryFreeColumnType.TOTAL, MemoryFreeRowType.SWAP),
        FREE_SWAP(MemoryFreeColumnType.FREE, MemoryFreeRowType.SWAP);

        private MemoryFreeColumnType memoryFreeColumnType;
        private final MemoryFreeRowType memoryFreeRowType;

        MemoryFreeType(
                MemoryFreeColumnType memoryFreeColumnType,
                MemoryFreeRowType memoryFreeRowType
        )
        {
            this.memoryFreeColumnType = memoryFreeColumnType;
            this.memoryFreeRowType = memoryFreeRowType;
        }

        public MemoryFreeColumnType getMemoryFreeColumnType()
        {
            return memoryFreeColumnType;
        }

        public MemoryFreeRowType getMemoryFreeRowType()
        {
            return memoryFreeRowType;
        }
    }
}
