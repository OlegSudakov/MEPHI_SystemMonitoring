package com.sysmon.core.ruleengine.formula.logical;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrFormulaTest extends EmptyContextFormulaTest
{
    @Test
    public void falseOrFalseEqualsFalse()
    {
        Formula formula = ruleFormulaParser.parse("false || false");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void falseOrTrueEqualsTrue()
    {
        Formula formula = ruleFormulaParser.parse("false || true");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void trueOrFalseEqualsTrue()
    {
        Formula formula = ruleFormulaParser.parse("true || false");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void trueOrTrueEqualsTrue()
    {
        Formula formula = ruleFormulaParser.parse("true || true");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
