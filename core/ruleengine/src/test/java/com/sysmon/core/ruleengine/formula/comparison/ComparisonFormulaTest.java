package com.sysmon.core.ruleengine.formula.comparison;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GreaterTest.class,
        LessTest.class,
        GreaterOrEqualTest.class,
        LessOrEqualTest.class,
        EqualTest.class,
        NotEqualTest.class
})
public class ComparisonFormulaTest
{
}
