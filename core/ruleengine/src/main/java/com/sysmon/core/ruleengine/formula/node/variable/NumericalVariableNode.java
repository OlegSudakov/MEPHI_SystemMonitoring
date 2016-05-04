package com.sysmon.core.ruleengine.formula.node.variable;

import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;

import java.math.BigDecimal;

public class NumericalVariableNode extends VariableNode<BigDecimal>
{
    public NumericalVariableNode(Object metricId)
    {
        super(metricId);
    }

    @Override
    public BigDecimal evaluate(FormulaEvaluationContext context)
    {
        return (BigDecimal) context.getMetricValue(metricId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No value for metric %d", metricId)));
    }
}
