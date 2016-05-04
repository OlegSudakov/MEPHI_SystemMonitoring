package com.sysmon.core.server.service;

import com.sysmon.core.dal.dao.DataTypeDao;
import com.sysmon.core.dal.dao.MetricTypeDao;
import com.sysmon.core.dal.entity.DataTypeEntity;
import com.sysmon.core.dal.entity.metric.MetricTypeEntity;
import com.sysmon.core.server.metric.MetricTypeManager;
import com.sysmon.core.server.utils.MetricTypeEntityToMetricTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricTypeServiceImpl implements MetricTypeService
{
    private final MetricTypeDao metricTypeDao;
    private final DataTypeDao dataTypeDao;
    private final MetricTypeEntityToMetricTypeConverter metricTypeEntityToMetricTypeConverter;
    private final MetricTypeManager metricTypeManager;

    @Autowired
    public MetricTypeServiceImpl(
            MetricTypeDao metricTypeDao,
            DataTypeDao dataTypeDao,
            MetricTypeEntityToMetricTypeConverter metricTypeEntityToMetricTypeConverter,
            MetricTypeManager metricTypeManager
    )
    {
        this.metricTypeDao = metricTypeDao;
        this.dataTypeDao = dataTypeDao;
        this.metricTypeEntityToMetricTypeConverter = metricTypeEntityToMetricTypeConverter;
        this.metricTypeManager = metricTypeManager;
    }

    @Override
    public void addMetricType(
            String name,
            Long dataTypeId
    )
    {
        DataTypeEntity dataTypeEntity = dataTypeDao.getById(dataTypeId).orElseThrow(() -> new IllegalArgumentException("Unknown data type"));

        MetricTypeEntity metricTypeEntity = new MetricTypeEntity();
        metricTypeEntity.setName(name);
        metricTypeEntity.setDataTypeEntity(dataTypeEntity);
        metricTypeEntity = metricTypeDao.save(metricTypeEntity);
        metricTypeManager.add(metricTypeEntityToMetricTypeConverter.apply(metricTypeEntity));
    }
}
