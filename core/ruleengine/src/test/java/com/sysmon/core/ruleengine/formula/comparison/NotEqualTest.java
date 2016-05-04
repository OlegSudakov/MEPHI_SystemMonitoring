package com.sysmon.core.ruleengine.formula.comparison;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotEqualTest extends EmptyContextFormulaTest
{
    @Test
    public void zeroNotEqualsZeroIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("0 != 0");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void oneNotEqualsOneIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("1 != 1");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void minusOneNotEqualsMinusOneIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("-1 != -1");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void zeroNotEqualsOneIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("0 != 1");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void oneNotEqualsZeroIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("1 != 0");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void floatingPointFiveNotEqualsIntegerFiveIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("5.0 != 5");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void floatingPointZeroNotEqualsIntegerZeroIsFalse()
    {
        Formula formula = ruleFormulaParser.parse(".0 != 0");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void floatingPointMinusFiveNotEqualsIntegerMinusFiveIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("-5.0 != -5");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
