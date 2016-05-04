package com.sysmon.core.ruleengine.formula.node.arithmetic;

import com.sysmon.core.ruleengine.formula.node.BiFuncionNode;
import com.sysmon.core.ruleengine.formula.node.FormulaNode;

import java.math.BigDecimal;

public abstract class ArithmeticBiFunctionNode extends BiFuncionNode<BigDecimal, BigDecimal, BigDecimal>
{
    public ArithmeticBiFunctionNode(
            FormulaNode<BigDecimal> leftNode,
            FormulaNode<BigDecimal> rightNode
    )
    {
        super(leftNode, rightNode);
    }
}
