package ru.sudakovoleg.dal.dao;

import ru.sudakovoleg.dal.metric.MetricEntity;

/**
 * Created by Oleg on 06.05.2016.
 */
public interface MetricDAO {
    public MetricEntity getMetricById(Long id);
}
