package com.sysmon.core.ruleengine.formula.node.comparison;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class LessOrEqualNode extends ComparisonBiFunctionNode
{
    public LessOrEqualNode(
            FormulaNode<BigDecimal> leftNode,
            FormulaNode<BigDecimal> rightNode
    )
    {
        super(leftNode, rightNode);
    }

    @Override
    public String toString()
    {
        return "(" + leftNode + " LE " + rightNode + ")";
    }

    @Override
    protected BiFunction<BigDecimal, BigDecimal, Boolean> function()
    {
        return (a,b) -> a.compareTo(b) <= 0;
    }
}