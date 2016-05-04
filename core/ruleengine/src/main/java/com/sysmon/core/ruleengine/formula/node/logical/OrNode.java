package com.sysmon.core.ruleengine.formula.node.logical;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;

import java.util.function.BiFunction;

public class OrNode extends LogicalBiFunctionNode
{
    public OrNode(
            FormulaNode<Boolean> leftNode,
            FormulaNode<Boolean> rightNode
    )
    {
        super(leftNode, rightNode);
    }

    @Override
    public String toString()
    {
        return "(" + leftNode + " || " + rightNode + ")";
    }

    @Override
    protected BiFunction<Boolean, Boolean, Boolean> function()
    {
        return (l,r) -> l || r;
    }
}
