package com.sysmon.core.ruleengine.formula.node.variable;

import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;
import com.sysmon.core.ruleengine.formula.node.FormulaNode;

public abstract class VariableNode<R> implements FormulaNode<R>
{
    protected final Object metricId;

    public VariableNode(Object metricId)
    {
        this.metricId = metricId;
    }

    @Override
    public String toString()
    {
        return "Variable(" + metricId + ")";
    }
}
