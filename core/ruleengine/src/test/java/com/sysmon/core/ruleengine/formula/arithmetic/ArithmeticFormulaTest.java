package com.sysmon.core.ruleengine.formula.arithmetic;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NumberTest.class,
        MultiplyTest.class,
        DivideTest.class,
        ModulusTest.class,
        AddTest.class,
        SubtractTest.class,
})
public class ArithmeticFormulaTest
{
}
