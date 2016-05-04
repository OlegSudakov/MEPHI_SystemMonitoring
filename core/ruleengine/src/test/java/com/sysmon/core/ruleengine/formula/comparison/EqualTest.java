package com.sysmon.core.ruleengine.formula.comparison;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualTest extends EmptyContextFormulaTest
{
    @Test
    public void zeroEqualsZeroIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("0 == 0");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void oneEqualsOneIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("1 == 1");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void minusOneEqualsMinusOneIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("-1 == -1");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void zeroEqualsOneIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("0 == 1");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void oneEqualsZeroIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("1 == 0");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void floatingPointFiveEqualsIntegerFiveIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("5.0 == 5");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void floatingPointZeroEqualsIntegerZeroIsTrue()
    {
        Formula formula = ruleFormulaParser.parse(".0 == 0");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void floatingPointMinusFiveEqualsIntegerMinusFiveIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("-5.0 == -5");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
