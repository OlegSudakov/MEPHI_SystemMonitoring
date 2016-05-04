package com.sysmon.core.ruleengine.formula.node.variable;

import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;

public class LogicalVariableNode extends VariableNode<Boolean>
{
    public LogicalVariableNode(Object metricId)
    {
        super(metricId);
    }

    @Override
    public Boolean evaluate(FormulaEvaluationContext context)
    {
        return (Boolean) context.getMetricValue(metricId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No value for metric %d", metricId)));
    }
}
