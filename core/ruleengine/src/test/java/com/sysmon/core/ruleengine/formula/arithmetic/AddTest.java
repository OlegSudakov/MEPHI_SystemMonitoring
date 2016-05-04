package com.sysmon.core.ruleengine.formula.arithmetic;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class AddTest extends EmptyContextFormulaTest
{
    @Test
    public void zeroAddZeroEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("0 + 0");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void zeroAddOneEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("0 + 1");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneAddZeroEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("1 + 0");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void minusOneAddOneEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("-1 + 1");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneAddMinusOneEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("1 + -1");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneAddOneEqualsTwo()
    {
        Formula formula = ruleFormulaParser.parse("1 + 1");
        assertTrue(new BigDecimal(2).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }
}
