package com.sysmon.core.ruleengine.formula.logical;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AndFormulaTest.class,
        OrFormulaTest.class,
        NotFormulaTest.class
})
public class LogicalFormulaTest
{
}
