package com.sysmon.core.ruleengine.formula.node.logical;

import com.sysmon.core.ruleengine.formula.node.FormulaNode;
import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;

public class TrueNode implements FormulaNode<Boolean>
{
    @Override
    public Boolean evaluate(FormulaEvaluationContext context)
    {
        return true;
    }

    @Override
    public String toString()
    {
        return "TRUE";
    }
}
