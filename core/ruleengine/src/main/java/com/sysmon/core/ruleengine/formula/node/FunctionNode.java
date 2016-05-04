package com.sysmon.core.ruleengine.formula.node;

import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;

import java.util.function.Function;

public abstract class FunctionNode<A, R> implements FormulaNode<R>
{
    protected abstract Function<A, R> function();

    protected final FormulaNode<A> node;

    public FunctionNode(FormulaNode<A> node)
    {
        this.node = node;
    }

    @Override
    public R evaluate(FormulaEvaluationContext context)
    {
        return function().apply(node.evaluate(context));
    }
}
