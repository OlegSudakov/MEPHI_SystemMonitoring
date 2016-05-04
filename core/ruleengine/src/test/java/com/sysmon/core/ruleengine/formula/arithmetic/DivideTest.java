package com.sysmon.core.ruleengine.formula.arithmetic;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class DivideTest extends EmptyContextFormulaTest
{
    @Test(expected = Throwable.class)
    public void zeroDivideZeroFails()
    {
        Formula formula = ruleFormulaParser.parse("0 / 0");
        formula.evaluate(formulaEvaluationContext);
    }

    @Test(expected = Throwable.class)
    public void oneDivideZeroFails()
    {
        Formula formula = ruleFormulaParser.parse("1 / 0");
        formula.evaluate(formulaEvaluationContext);
    }

    @Test
    public void zeroDivideOneEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("0 / 1");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneDivideOneEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("1 / 1");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void twelveDivideThreeEqualsFour()
    {
        Formula formula = ruleFormulaParser.parse("12 / 3");
        assertTrue(new BigDecimal(4).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void twoDivideFourEqualsPointFive()
    {
        Formula formula = ruleFormulaParser.parse("2 / 4");
        assertTrue(new BigDecimal(.5).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void twoPointFiveDividePointFiveEqualsFive()
    {
        Formula formula = ruleFormulaParser.parse("2.5 / .5");
        assertTrue(new BigDecimal(5).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }
}
