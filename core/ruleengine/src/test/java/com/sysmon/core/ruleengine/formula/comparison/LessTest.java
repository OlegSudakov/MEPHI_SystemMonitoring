package com.sysmon.core.ruleengine.formula.comparison;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LessTest extends EmptyContextFormulaTest
{
    @Test
    public void zeroLessZeroIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("0 < 0");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void zeroLessOneIsTrue()
    {
        Formula formula = ruleFormulaParser.parse("0 < 1");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void oneLessZeroIsFalse()
    {
        Formula formula = ruleFormulaParser.parse("1 < 0");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
