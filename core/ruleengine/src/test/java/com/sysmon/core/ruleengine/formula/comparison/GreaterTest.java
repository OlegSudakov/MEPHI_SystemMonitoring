package com.sysmon.core.ruleengine.formula.comparison;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GreaterTest extends EmptyContextFormulaTest
{
    @Test
    public void zeroGreaterZeroIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("0 > 0");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void zeroGreaterOneIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("0 > 1");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void oneGreaterZeroIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("1 > 0");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
