package com.sysmon.core.ruleengine.formula.node;

import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;

import java.util.function.BiFunction;

public abstract class BiFuncionNode<A, B, R> implements FormulaNode<R>
{
    protected abstract BiFunction<A, B, R> function();

    protected final FormulaNode<A> leftNode;
    protected final FormulaNode<B> rightNode;

    public BiFuncionNode(
            FormulaNode<A> leftNode,
            FormulaNode<B> rightNode
    )
    {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public R evaluate(FormulaEvaluationContext context)
    {
        return function().apply(leftNode.evaluate(context), rightNode.evaluate(context));
    }
}
