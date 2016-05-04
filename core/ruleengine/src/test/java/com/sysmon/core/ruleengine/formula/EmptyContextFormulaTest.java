package com.sysmon.core.ruleengine.formula;

import com.sysmon.core.ruleengine.parser.RuleFormulaParser;
import com.sysmon.core.ruleengine.parser.RuleFormulaParserImpl;

public class EmptyContextFormulaTest
{
    protected RuleFormulaParser ruleFormulaParser = new RuleFormulaParserImpl();
    protected FormulaEvaluationContext formulaEvaluationContext = FormulaEvaluationContext.empty();
}
