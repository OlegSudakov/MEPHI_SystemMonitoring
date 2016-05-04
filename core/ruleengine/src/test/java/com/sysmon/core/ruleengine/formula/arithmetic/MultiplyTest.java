package com.sysmon.core.ruleengine.formula.arithmetic;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class MultiplyTest extends EmptyContextFormulaTest
{
    @Test
    public void zeroMultiplyZeroEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("0 * 0");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void zeroMultiplyOneEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("0 * 1");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneMultiplyZeroEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("1 * 0");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void minusOneMultiplyOneEqualsMinusOne()
    {
        Formula formula = ruleFormulaParser.parse("-1 * 1");
        assertTrue(new BigDecimal(-1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneMultiplyMinusOneEqualsMinusOne()
    {
        Formula formula = ruleFormulaParser.parse("1 * -1");
        assertTrue(new BigDecimal(-1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneMultiplyOneEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("1 * 1");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void threeMultiplyFourEqualsTwelve()
    {
        Formula formula = ruleFormulaParser.parse("3 * 4");
        assertTrue(new BigDecimal(12).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void fourMultiplyThreeEqualsTwelve()
    {
        Formula formula = ruleFormulaParser.parse("4 * 3");
        assertTrue(new BigDecimal(12).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void twoPointFiveMultiplyThreePointFiveEqualsEightPointSeventyFive()
    {
        Formula formula = ruleFormulaParser.parse("2.5 * 3.5");
        assertTrue(new BigDecimal(8.75).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }
}
