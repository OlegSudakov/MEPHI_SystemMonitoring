package com.sysmon.core.ruleengine.formula.arithmetic;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class ModulusTest extends EmptyContextFormulaTest
{
    @Test(expected = Throwable.class)
    public void zeroModulusZeroFails()
    {
        Formula formula = ruleFormulaParser.parse("0 % 0");
        formula.evaluate(formulaEvaluationContext);
    }

    @Test(expected = Throwable.class)
    public void oneModulusZeroFails()
    {
        Formula formula = ruleFormulaParser.parse("1 % 0");
        formula.evaluate(formulaEvaluationContext);
    }

    @Test
    public void zeroModulusOneEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("0 % 1");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneModulusOneEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("1 % 1");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void oneModulusThreeEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("1 % 3");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void threeModulusOneEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("3 % 1");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void twoModulusFourEqualsTwo()
    {
        Formula formula = ruleFormulaParser.parse("2 % 4");
        assertTrue(new BigDecimal(2).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void twelveModulusThreeEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("12 % 3");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void fiveModulusTwoEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("5 % 2");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void eightModulusThreeEqualsTwo()
    {
        Formula formula = ruleFormulaParser.parse("8 % 3");
        assertTrue(new BigDecimal(2).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void minusFiveModulusFiveEqualsZero()
    {
        Formula formula = ruleFormulaParser.parse("-5 % 5");
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void minusFiveModulusEightEqualsThree()
    {
        Formula formula = ruleFormulaParser.parse("-5 % 8");
        assertTrue(new BigDecimal(3).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void minusTwoModulusThreeEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("-2 % 3");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test
    public void minusFiveModulusThreeEqualsOne()
    {
        Formula formula = ruleFormulaParser.parse("-5 % 3");
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(formulaEvaluationContext)) == 0);
    }

    @Test(expected = Throwable.class)
    public void fiveModulusMinusFiveFails()
    {
        Formula formula = ruleFormulaParser.parse("5 % -5");
        formula.evaluate(formulaEvaluationContext);
    }

    @Test(expected = Throwable.class)
    public void fiveModulusMinusEightFails()
    {
        Formula formula = ruleFormulaParser.parse("5 % -8");
        formula.evaluate(formulaEvaluationContext);
    }

    @Test(expected = Throwable.class)
    public void fiveModulusMinusThreeFails()
    {
        Formula formula = ruleFormulaParser.parse("5 % -3");
        formula.evaluate(formulaEvaluationContext);
    }
}
