package com.sysmon.core.ruleengine.formula;

import com.sysmon.core.ruleengine.formula.arithmetic.ArithmeticFormulaTest;
import com.sysmon.core.ruleengine.formula.comparison.ComparisonFormulaTest;
import com.sysmon.core.ruleengine.formula.logical.LogicalFormulaTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArithmeticFormulaTest.class,
        LogicalFormulaTest.class,
        ComparisonFormulaTest.class,
})
public class FormulaTest
{
}
