package com.sysmon.core.ruleengine.formula.logical;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConstantTest extends EmptyContextFormulaTest
{
    @Test
    public void trueEqualsTrue()
    {
        Formula formula = ruleFormulaParser.parse("true");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void falseEqualsFalse()
    {
        Formula formula = ruleFormulaParser.parse("false");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
