package com.sysmon.core.ruleengine.formula.node.logical;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;
import com.sysmon.core.ruleengine.formula.node.FunctionNode;

public abstract class LogicalFunctionNode extends FunctionNode<Boolean, Boolean>
{
    public LogicalFunctionNode(FormulaNode<Boolean> leftNode)
    {
        super(leftNode);
    }
}
