package com.sysmon.core.ruleengine.formula.logical;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AndFormulaTest extends EmptyContextFormulaTest
{
    @Test
    public void falseAndFalseEqualsFalse()
    {
        Formula formula = ruleFormulaParser.parse("false && false");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void falseAndTrueEqualsFalse()
    {
        Formula formula = ruleFormulaParser.parse("false && true");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void trueAndFalseEqualsFalse()
    {
        Formula formula = ruleFormulaParser.parse("true && false");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void trueAndTrueEqualsTrue()
    {
        Formula formula = ruleFormulaParser.parse("true && true");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
