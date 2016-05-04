package com.sysmon.core.ruleengine.formula.logical;

import com.sysmon.core.ruleengine.formula.EmptyContextFormulaTest;
import com.sysmon.core.ruleengine.formula.Formula;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotFormulaTest extends EmptyContextFormulaTest
{
    @Test
    public void notTrueEqualsFalse()
    {
        Formula formula = ruleFormulaParser.parse("! true");
        assertFalse((Boolean) formula.evaluate(formulaEvaluationContext));
    }

    @Test
    public void notFalseEqualsTrue()
    {
        Formula formula = ruleFormulaParser.parse("! false");
        assertTrue((Boolean) formula.evaluate(formulaEvaluationContext));
    }
}
