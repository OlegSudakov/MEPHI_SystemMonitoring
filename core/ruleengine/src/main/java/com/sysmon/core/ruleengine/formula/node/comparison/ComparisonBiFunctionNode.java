package com.sysmon.core.ruleengine.formula.node.comparison;

import com.sysmon.core.ruleengine.formula.node.BiFuncionNode;
import com.sysmon.core.ruleengine.formula.node.FormulaNode;

import java.math.BigDecimal;

public abstract class ComparisonBiFunctionNode extends BiFuncionNode<BigDecimal, BigDecimal, Boolean>
{
    public ComparisonBiFunctionNode(
            FormulaNode<BigDecimal> leftNode,
            FormulaNode<BigDecimal> rightNode
    )
    {
        super(leftNode, rightNode);
    }
}
