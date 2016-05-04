package com.sysmon.core.ruleengine.parser;

import com.sysmon.core.ruleengine.formula.Formula;

public interface RuleFormulaParser
{
    void setDataTypeForKey(
            Object key,
            DataType dataType
    );

    Formula parse(String formulaString);
}
