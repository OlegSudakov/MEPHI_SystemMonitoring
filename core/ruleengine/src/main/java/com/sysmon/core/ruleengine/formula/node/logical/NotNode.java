package com.sysmon.core.ruleengine.formula.node.logical;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;

import java.util.function.Function;

public class NotNode extends LogicalFunctionNode
{
    public NotNode(FormulaNode<Boolean> node)
    {
        super(node);
    }

    @Override
    public String toString()
    {
        return "NOT(" + node + ")";
    }

    @Override
    protected Function<Boolean, Boolean> function()
    {
        return a -> !a;
    }
}
