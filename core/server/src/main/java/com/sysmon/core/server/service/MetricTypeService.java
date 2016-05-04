package com.sysmon.core.server.service;

public interface MetricTypeService
{
    void addMetricType(
            String name,
            Long dataTypeId
    );
}
