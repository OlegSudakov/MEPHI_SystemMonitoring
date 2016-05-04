package com.sysmon.core.ruleengine.parser;

import com.sysmon.core.ruleengine.antlr.RuleFormulaBaseListener;
import com.sysmon.core.ruleengine.antlr.RuleFormulaLexer;
import com.sysmon.core.ruleengine.formula.Formula;
import com.sysmon.core.ruleengine.formula.node.FormulaNode;
import com.sysmon.core.ruleengine.formula.node.arithmetic.AddNode;
import com.sysmon.core.ruleengine.formula.node.arithmetic.DivideNode;
import com.sysmon.core.ruleengine.formula.node.arithmetic.ModulusNode;
import com.sysmon.core.ruleengine.formula.node.arithmetic.MuliplyNode;
import com.sysmon.core.ruleengine.formula.node.arithmetic.NumberNode;
import com.sysmon.core.ruleengine.formula.node.arithmetic.SubtractNode;
import com.sysmon.core.ruleengine.formula.node.comparison.EqualNode;
import com.sysmon.core.ruleengine.formula.node.comparison.GreaterNode;
import com.sysmon.core.ruleengine.formula.node.comparison.GreaterOrEqualNode;
import com.sysmon.core.ruleengine.formula.node.comparison.LessNode;
import com.sysmon.core.ruleengine.formula.node.comparison.LessOrEqualNode;
import com.sysmon.core.ruleengine.formula.node.comparison.NotEqualNode;
import com.sysmon.core.ruleengine.formula.node.logical.AndNode;
import com.sysmon.core.ruleengine.formula.node.logical.FalseNode;
import com.sysmon.core.ruleengine.formula.node.logical.NotNode;
import com.sysmon.core.ruleengine.formula.node.logical.OrNode;
import com.sysmon.core.ruleengine.formula.node.logical.TrueNode;
import com.sysmon.core.ruleengine.formula.node.variable.LogicalVariableNode;
import com.sysmon.core.ruleengine.formula.node.variable.NumericalVariableNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class RuleFormulaParserImpl implements RuleFormulaParser
{
    private ConcurrentMap<Object, DataType> dataTypeByKey;

    public RuleFormulaParserImpl()
    {
        this.dataTypeByKey = new ConcurrentHashMap<>();
    }

    public RuleFormulaParserImpl(Map<Object, DataType> dataTypeByKey)
    {
        this.dataTypeByKey = new ConcurrentHashMap<>(dataTypeByKey);
    }

    @Override
    public void setDataTypeForKey(
            Object key,
            DataType dataType
    )
    {
        dataTypeByKey.put(key, dataType);
    }

    @Override
    public Formula parse(String formulaString)
    {
        RuleFormulaLexer lexer = new RuleFormulaLexer(new ANTLRInputStream(formulaString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        com.sysmon.core.ruleengine.antlr.RuleFormulaParser parser = new com.sysmon.core.ruleengine.antlr.RuleFormulaParser(tokens);

        ParseTree tree = parser.expression();
        ExpressionTreeWalker expressionTreeWalker = new ExpressionTreeWalker(dataTypeByKey);
        ParseTreeWalker.DEFAULT.walk(expressionTreeWalker, tree);
        return expressionTreeWalker.getFormula();
    }

    private static class ExpressionTreeWalker extends RuleFormulaBaseListener
    {
        private final Stack<FormulaNode> stack = new Stack<>();
        private final Map<Object, DataType> dataTypeByKey;

        public ExpressionTreeWalker(Map<Object, DataType> dataTypeByKey)
        {
            this.dataTypeByKey = dataTypeByKey != null ? new HashMap<>(dataTypeByKey) : new HashMap<>();
        }

        public Formula getFormula()
        {
            if (stack.size() != 1) {
                throw new IllegalStateException(String.format("Internal error: formula node stack size is not 1. Current size is %d", stack.size()));
            }
            return new Formula(stack.pop());
        }

        @Override
        public void enterExpression(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.ExpressionContext ctx)
        {
            super.enterExpression(ctx);
        }

        @Override
        public void exitExpression(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.ExpressionContext ctx)
        {
            if (ctx.exception != null) {
                throw ctx.exception;
            }
            if (ctx.MUL() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new MuliplyNode(leftNode, rightNode));
            } else if (ctx.DIV() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new DivideNode(leftNode, rightNode));
            } else if (ctx.MOD() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new ModulusNode(leftNode, rightNode));
            } else if (ctx.ADD() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new AddNode(leftNode, rightNode));
            } else if (ctx.SUB() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new SubtractNode(leftNode, rightNode));
            } else if (ctx.GT() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new GreaterNode(leftNode, rightNode));
            } else if (ctx.LT() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new LessNode(leftNode, rightNode));
            } else if (ctx.GE() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new GreaterOrEqualNode(leftNode, rightNode));
            } else if (ctx.LE() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new LessOrEqualNode(leftNode, rightNode));
            } else if (ctx.EQUAL() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new EqualNode(leftNode, rightNode));
            } else if (ctx.NOTEQUAL() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new NotEqualNode(leftNode, rightNode));
            } else if (ctx.BANG() != null) {
                FormulaNode node = stack.pop();
                stack.push(new NotNode(node));
            } else if (ctx.AND() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new AndNode(leftNode, rightNode));
            } else if (ctx.OR() != null) {
                FormulaNode rightNode = stack.pop();
                FormulaNode leftNode = stack.pop();
                stack.push(new OrNode(leftNode, rightNode));
            } else if (ctx.literal() != null ||
                       ctx.par_expression() != null ||
                       ctx.measure_query_expression() != null) {
            } else {
                throw new IllegalStateException(String.format("Unexpected expression: %s", ctx.getText()));
            }
            super.exitExpression(ctx);
        }

        @Override
        public void enterMeasure_query_expression(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.Measure_query_expressionContext ctx)
        {
            super.enterMeasure_query_expression(ctx);
        }

        @Override
        public void exitMeasure_query_expression(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.Measure_query_expressionContext ctx)
        {
            StringBuilder keyBuilder = new StringBuilder(ctx.getText().length())
                    .append(ctx.metricIdentifier().instanceIdentifier().getText())
                    .append(".")
                    .append(ctx.metricIdentifier().metricName().getText())
                    .append(".");
            com.sysmon.core.ruleengine.antlr.RuleFormulaParser.Measure_query_functionContext measureFunctionContext = ctx.measure_query_function();
            if (measureFunctionContext.CurrentFunctionName() != null) {
                keyBuilder.append(measureFunctionContext.CurrentFunctionName().getText());
                keyBuilder.append(measureFunctionContext.LPAREN().getText());
                keyBuilder.append(measureFunctionContext.RPAREN().getText());
            } else if (measureFunctionContext.LastFunctionName() != null) {
                keyBuilder.append(measureFunctionContext.LastFunctionName().getText());
                keyBuilder.append(measureFunctionContext.LPAREN().getText());
                keyBuilder.append(measureFunctionContext.RPAREN().getText());
            } else if (measureFunctionContext.AvgFunctionName() != null) {
                keyBuilder.append(measureFunctionContext.AvgFunctionName().getText());
                keyBuilder.append(measureFunctionContext.LPAREN().getText());
                keyBuilder.append(measureFunctionContext.interval().getText());
                keyBuilder.append(measureFunctionContext.RPAREN().getText());
            } else if (measureFunctionContext.MinFunctionName() != null) {
                keyBuilder.append(measureFunctionContext.MinFunctionName().getText());
                keyBuilder.append(measureFunctionContext.LPAREN().getText());
                keyBuilder.append(measureFunctionContext.interval().getText());
                keyBuilder.append(measureFunctionContext.RPAREN().getText());
            } else if (measureFunctionContext.MaxFunctionName() != null) {
                keyBuilder.append(measureFunctionContext.MaxFunctionName().getText());
                keyBuilder.append(measureFunctionContext.LPAREN().getText());
                keyBuilder.append(measureFunctionContext.interval().getText());
                keyBuilder.append(measureFunctionContext.RPAREN().getText());
            } else if (measureFunctionContext.PercentileFunctionName() != null) {
                keyBuilder.append(measureFunctionContext.PercentileFunctionName().getText());
                keyBuilder.append(measureFunctionContext.LPAREN().getText());
                keyBuilder.append(measureFunctionContext.interval().getText());
                keyBuilder.append(measureFunctionContext.COMMA().getText());
                keyBuilder.append(measureFunctionContext.FloatingPointLiteral().getText());
                keyBuilder.append(measureFunctionContext.RPAREN().getText());
            } else {
                throw new IllegalArgumentException(String.format("Unexpected function: %s", measureFunctionContext.getText()));
            }
            String key = keyBuilder.toString();
            DataType dataType = Optional.ofNullable(dataTypeByKey.get(key)).orElseThrow(() -> new IllegalArgumentException(String.format("Unknown key %s", key)));
            switch (dataType) {
                case LOGICAL:
                    stack.push(new LogicalVariableNode(key));
                    break;
                case NUMERICAL:
                    stack.push(new NumericalVariableNode(key));
                    break;
            }
            super.exitMeasure_query_expression(ctx);
        }

        @Override
        public void enterMeasure_query_function(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.Measure_query_functionContext ctx)
        {
            super.enterMeasure_query_function(ctx);
        }

        @Override
        public void exitMeasure_query_function(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.Measure_query_functionContext ctx)
        {
            super.exitMeasure_query_function(ctx);
        }

        @Override
        public void enterLiteral(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.LiteralContext ctx)
        {
            super.enterLiteral(ctx);
        }

        @Override
        public void exitLiteral(com.sysmon.core.ruleengine.antlr.RuleFormulaParser.LiteralContext ctx)
        {
            if (ctx.BooleanLiteral() != null) {
                if (ctx.BooleanLiteral().getText().equals("true")) {
                    stack.push(new TrueNode());
                } else if (ctx.BooleanLiteral().getText().equals("false")) {
                    stack.push(new FalseNode());
                } else {
                    throw new IllegalStateException(String.format("Unexpected expression: %s", ctx.BooleanLiteral().getText()));
                }
            } else if (ctx.IntegerLiteral() != null || ctx.FloatingPointLiteral() != null) {
                stack.push(new NumberNode(new BigDecimal(ctx.getText())));
            }
            super.exitLiteral(ctx);
        }
    }
}
