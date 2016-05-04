package com.sysmon.core.ruleengine.formula;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;

public class Formula<R>
{
    private final FormulaNode<R> rootNode;

    public Formula(FormulaNode<R> rootNode)
    {
        this.rootNode = rootNode;
    }

    @Override
    public String toString()
    {
        return "Formula=(" + rootNode + ")";
    }

    public R evaluate(FormulaEvaluationContext context)
    {
        return rootNode.evaluate(context);
    }
}
