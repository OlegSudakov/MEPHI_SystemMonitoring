package com.sysmon.core.ruleengine.formula.metric;

import com.sysmon.core.ruleengine.formula.Formula;
import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;
import com.sysmon.core.ruleengine.parser.DataType;
import com.sysmon.core.ruleengine.parser.RuleFormulaParser;
import com.sysmon.core.ruleengine.parser.RuleFormulaParserImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogicalMetricTest
{
    protected RuleFormulaParser ruleFormulaParser = new RuleFormulaParserImpl();

    @Test
    public void testMetricCurrentWithTrueEqualsTrue()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.CURRENT()", DataType.LOGICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.CURRENT()");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.CURRENT()", true);
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue((Boolean) formula.evaluate(context));
    }

    @Test
    public void testMetricCurrentWithFalseEqualsFalse()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.CURRENT()", DataType.LOGICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.CURRENT()");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.CURRENT()", false);
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertFalse((Boolean) formula.evaluate(context));
    }

    @Test
    public void testMetricLastWithTrueEqualsTrue()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.LAST()", DataType.LOGICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.LAST()");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.LAST()", true);
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue((Boolean) formula.evaluate(context));
    }

    @Test
    public void testMetricLastWithFalseEqualsFalse()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.LAST()", DataType.LOGICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.LAST()");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.LAST()", false);
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertFalse((Boolean) formula.evaluate(context));
    }
}
