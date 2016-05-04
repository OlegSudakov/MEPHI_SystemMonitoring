package com.sysmon.core.ruleengine.formula.arithmetic;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;

public class NumberTest extends EmptyContextFormulaTest
{
    @Test
    public void integerZeroEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("0");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void integerOneEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("1");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void integerMinusOneEqualsMinusOne()
    {
        Formula formula = ruleFormulaParser.parse("-1");
        assertTrue(new BigDecimal(-1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void integerTenEqualsTen()
    {
        Formula formula = ruleFormulaParser.parse("10");
        assertTrue(new BigDecimal(10).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void integerMinusTenEqualsMinusTen()
    {
        Formula formula = ruleFormulaParser.parse("-10");
        assertTrue(new BigDecimal(-10).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void floatingPointZeroEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("0.0");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void floatingPointOneEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("1.0");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void floatingPointMinusOneEqualsMinusOne()
    {
        Formula formula = ruleFormulaParser.parse("-1.0");
        assertTrue(new BigDecimal(-1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void floatingPointTenEqualsTen()
    {
        Formula formula = ruleFormulaParser.parse("10.0");
        assertTrue(new BigDecimal(10).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void floatingPointMinusTenEqualsMinusTen()
    {
        Formula formula = ruleFormulaParser.parse("-10.0");
        assertTrue(new BigDecimal(-10).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }
}
