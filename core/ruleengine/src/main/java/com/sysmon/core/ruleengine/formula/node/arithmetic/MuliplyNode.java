package com.sysmon.core.ruleengine.formula.node.arithmetic;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class MuliplyNode extends ArithmeticBiFunctionNode
{
    public MuliplyNode(
            FormulaNode<BigDecimal> leftNode,
            FormulaNode<BigDecimal> rightNode
    )
    {
        super(leftNode, rightNode);
    }

    @Override
    public String toString()
    {
        return "(" + leftNode + " * " + rightNode + ")";
    }

    @Override
    protected BiFunction<BigDecimal, BigDecimal, BigDecimal> function()
    {
        return (a,b) -> a.multiply(b);
    }
}
