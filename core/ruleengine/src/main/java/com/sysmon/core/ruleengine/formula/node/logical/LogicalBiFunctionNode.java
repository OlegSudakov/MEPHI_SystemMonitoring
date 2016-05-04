package com.sysmon.core.ruleengine.formula.node.logical;

import com.sysmon.core.ruleengine.formula.node.BiFuncionNode;
import com.sysmon.core.ruleengine.formula.node.FormulaNode;

public abstract class LogicalBiFunctionNode extends BiFuncionNode<Boolean, Boolean, Boolean>
{
    public LogicalBiFunctionNode(
            FormulaNode<Boolean> leftNode,
            FormulaNode<Boolean> rightNode
    )
    {
        super(leftNode, rightNode);
    }
}
