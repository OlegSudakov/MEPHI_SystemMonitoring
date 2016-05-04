package com.sysmon.core.ruleengine.formula.node.arithmetic;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;
import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;

import java.math.BigDecimal;

public class NumberNode implements FormulaNode<BigDecimal>
{
    private final BigDecimal value;

    public NumberNode(BigDecimal value)
    {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate(FormulaEvaluationContext context)
    {
        return value;
    }

    @Override
    public String toString()
    {
        return value.toString();
    }
}
