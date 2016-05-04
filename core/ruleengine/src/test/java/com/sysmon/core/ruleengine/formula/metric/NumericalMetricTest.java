package com.sysmon.core.ruleengine.formula.metric;

import com.sysmon.core.ruleengine.formula.Formula;
import com.sysmon.core.ruleengine.formula.FormulaEvaluationContext;
import com.sysmon.core.ruleengine.parser.DataType;
import com.sysmon.core.ruleengine.parser.RuleFormulaParser;
import com.sysmon.core.ruleengine.parser.RuleFormulaParserImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class NumericalMetricTest
{
    protected RuleFormulaParser ruleFormulaParser = new RuleFormulaParserImpl();

    @Test
    public void testMetricCurrentWithOneEqualsOne()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.CURRENT()", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.CURRENT()");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.CURRENT()", new BigDecimal(1));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(1).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricCurrentWithFiveEqualsFive()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.CURRENT()", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.CURRENT()");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.CURRENT()", new BigDecimal(5));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(5).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricLastWithZeroEqualsZero()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.LAST()", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.LAST()");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.LAST()", new BigDecimal(0));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(0).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricAverageWithThreeEqualsThree()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.AVG(5)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.AVG(5)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.AVG(5)", new BigDecimal(3));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(3).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricAverageWithTwoEqualsTwo()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.AVG(14)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.AVG(14)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.AVG(14)", new BigDecimal(2));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(2).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricMinimumWithMinusOneEqualsMinusOne()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.MIN(-1)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.MIN(-1)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.MIN(-1)", new BigDecimal(-1));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(-1).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricMinimumWithTenEqualsTen()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.MIN(10)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.MIN(10)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.MIN(10)", new BigDecimal(10));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(10).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricMaximumWithMinusOneEqualsMinusOne()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.MAX(-1)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.MAX(-1)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.MAX(-1)", new BigDecimal(-1));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(-1).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricMaximumWithTenEqualsTen()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.MAX(10)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.MAX(10)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.MAX(10)", new BigDecimal(10));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(10).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricPercentileWithMinusOneEqualsMinusOne()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.PERCENTILE(-1,0.65)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.PERCENTILE(-1,0.65)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.PERCENTILE(-1,0.65)", new BigDecimal(-1));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(-1).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }

    @Test
    public void testMetricPercentileWithTenEqualsTen()
    {
        ruleFormulaParser.setDataTypeForKey("instance.metric.PERCENTILE(10,0.5)", DataType.NUMERICAL);
        Formula formula = ruleFormulaParser.parse("instance.metric.PERCENTILE(10,0.5)");
        Map<Object, Object> metricValuesById = new HashMap<>();
        metricValuesById.put("instance.metric.PERCENTILE(10,0.5)", new BigDecimal(10));
        FormulaEvaluationContext context = new FormulaEvaluationContext(metricValuesById);
        assertTrue(new BigDecimal(10).compareTo((BigDecimal) formula.evaluate(context)) == 0);
    }
}
