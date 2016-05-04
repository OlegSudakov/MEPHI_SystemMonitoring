package com.sysmon.core.ruleengine.formula.node;

import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;

public interface FormulaNode<R>
{
    R evaluate(FormulaEvaluationContext context);
}
