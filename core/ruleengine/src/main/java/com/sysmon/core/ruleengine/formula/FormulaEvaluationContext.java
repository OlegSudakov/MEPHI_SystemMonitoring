package com.sysmon.core.ruleengine.formula;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FormulaEvaluationContext
{
    private final Map<Object, Object> metricValueById;

    public static FormulaEvaluationContext empty()
    {
        return new FormulaEvaluationContext(new HashMap<>());
    }

    public FormulaEvaluationContext(Map<Object, Object> metricValueById)
    {
        this.metricValueById = new HashMap<>(metricValueById);
    }

    public Optional<Object> getMetricValue(Object metricId)
    {
        return Optional.ofNullable(metricValueById.get(metricId));
    }
}
