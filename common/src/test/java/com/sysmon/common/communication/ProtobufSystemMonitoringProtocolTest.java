package com.sysmon.common.communication;

import com.sysmon.common.communication.ProtobufSystemMonitoringProtocol.*;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProtobufSystemMonitoringProtocolTest
{
    @Test
    public void oneDoubleMessageToByteArrayAndBackToMessage() throws InvalidProtocolBufferException
    {
        MeasureBundle message =
                MeasureBundle.newBuilder()
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(1)
                                        .setTime(123456789)
                                        .setDoubleValue(.5)
                                        .build()
                        ).build();
        byte[] byteArray = message.toByteArray();
        MeasureBundle parsedMessage = MeasureBundle.parseFrom(byteArray);
        List<MeasureBundle.Measure> measureMessageList = parsedMessage.getMeasuresList();
        assertTrue(measureMessageList.size() == 1);
        MeasureBundle.Measure measure = measureMessageList.get(0);

        assertTrue(measure.getMetricId() == 1);
        assertTrue(measure.getTime() == 123456789);
        assertTrue(measure.hasDoubleValue());
        assertTrue(measure.getDoubleValue() == .5);

        assertFalse(measure.hasFloatValue());
        assertFalse(measure.hasBooleanValue());
        assertFalse(measure.hasLongValue());
        assertFalse(measure.hasIntValue());
    }

    @Test
    public void oneFloatMessageToByteArrayAndBackToMessage() throws InvalidProtocolBufferException
    {
        MeasureBundle message =
                MeasureBundle.newBuilder()
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(1)
                                        .setTime(123456789)
                                        .setFloatValue(.5f)
                                        .build()
                        ).build();
        byte[] byteArray = message.toByteArray();
        MeasureBundle parsedMessage = MeasureBundle.parseFrom(byteArray);
        List<MeasureBundle.Measure> measureMessageList = parsedMessage.getMeasuresList();
        assertTrue(measureMessageList.size() == 1);
        MeasureBundle.Measure measure = measureMessageList.get(0);

        assertTrue(measure.getMetricId() == 1);
        assertTrue(measure.getTime() == 123456789);
        assertTrue(measure.hasFloatValue());
        assertTrue(measure.getFloatValue() == .5f);

        assertFalse(measure.hasDoubleValue());
        assertFalse(measure.hasBooleanValue());
        assertFalse(measure.hasLongValue());
        assertFalse(measure.hasIntValue());
    }

    @Test
    public void oneBooleanMessageToByteArrayAndBackToMessage() throws InvalidProtocolBufferException
    {
        MeasureBundle message =
                MeasureBundle.newBuilder()
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(1)
                                        .setTime(123456789)
                                        .setBooleanValue(false)
                                        .build()
                        ).build();
        byte[] byteArray = message.toByteArray();
        MeasureBundle parsedMessage = MeasureBundle.parseFrom(byteArray);
        List<MeasureBundle.Measure> measureMessageList = parsedMessage.getMeasuresList();
        assertTrue(measureMessageList.size() == 1);
        MeasureBundle.Measure measure = measureMessageList.get(0);

        assertTrue(measure.getMetricId() == 1);
        assertTrue(measure.getTime() == 123456789);
        assertTrue(measure.hasBooleanValue());
        assertTrue(measure.getBooleanValue() == false);

        assertFalse(measure.hasDoubleValue());
        assertFalse(measure.hasFloatValue());
        assertFalse(measure.hasLongValue());
        assertFalse(measure.hasIntValue());
    }

    @Test
    public void oneLongMessageToByteArrayAndBackToMessage() throws InvalidProtocolBufferException
    {
        MeasureBundle message =
                MeasureBundle.newBuilder()
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(1)
                                        .setTime(123456789)
                                        .setLongValue(777)
                                        .build()
                        ).build();
        byte[] byteArray = message.toByteArray();
        MeasureBundle parsedMessage = MeasureBundle.parseFrom(byteArray);
        List<MeasureBundle.Measure> measureMessageList = parsedMessage.getMeasuresList();
        assertTrue(measureMessageList.size() == 1);
        MeasureBundle.Measure measure = measureMessageList.get(0);

        assertTrue(measure.getMetricId() == 1);
        assertTrue(measure.getTime() == 123456789);
        assertTrue(measure.hasLongValue());
        assertTrue(measure.getLongValue() == 777);

        assertFalse(measure.hasDoubleValue());
        assertFalse(measure.hasFloatValue());
        assertFalse(measure.hasBooleanValue());
        assertFalse(measure.hasIntValue());
    }

    @Test
    public void oneIntegerMessageToByteArrayAndBackToMessage() throws InvalidProtocolBufferException
    {
        MeasureBundle message =
                MeasureBundle.newBuilder()
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(1)
                                        .setTime(123456789)
                                        .setIntValue(777)
                                        .build()
                        ).build();
        byte[] byteArray = message.toByteArray();
        MeasureBundle parsedMessage = MeasureBundle.parseFrom(byteArray);
        List<MeasureBundle.Measure> measureMessageList = parsedMessage.getMeasuresList();
        assertTrue(measureMessageList.size() == 1);
        MeasureBundle.Measure measure = measureMessageList.get(0);

        assertTrue(measure.getMetricId() == 1);
        assertTrue(measure.getTime() == 123456789);
        assertTrue(measure.hasIntValue());
        assertTrue(measure.getIntValue() == 777);

        assertFalse(measure.hasDoubleValue());
        assertFalse(measure.hasFloatValue());
        assertFalse(measure.hasBooleanValue());
        assertFalse(measure.hasLongValue());
    }

    @Test
    public void threeMessagesToByteArrayAndBackToMessages() throws InvalidProtocolBufferException
    {
        MeasureBundle message =
                MeasureBundle.newBuilder()
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(23)
                                        .setTime(6548)
                                        .setDoubleValue(1.79)
                                        .build()
                        )
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(7)
                                        .setTime(6548)
                                        .setFloatValue(789f)
                                        .build()
                        )
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(5)
                                        .setTime(85)
                                        .setBooleanValue(true)
                                        .build()
                        )
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(78)
                                        .setTime(659)
                                        .setLongValue(765)
                                        .build()
                        )
                        .addMeasures(
                                MeasureBundle.Measure.newBuilder()
                                        .setMetricId(123)
                                        .setTime(659)
                                        .setIntValue(134)
                                        .build()
                        )
                        .build();
        byte[] byteArray = message.toByteArray();
        MeasureBundle parsedMessage = MeasureBundle.parseFrom(byteArray);
        List<MeasureBundle.Measure> measureMessageList = parsedMessage.getMeasuresList();
        assertTrue(measureMessageList.size() == 5);
        boolean firstMessageParsed = false;
        boolean secondMessageParsed = false;
        boolean thirdMessageParsed = false;
        boolean forthMessageParsed = false;
        boolean fifthMessageParsed = false;
        for (MeasureBundle.Measure measure : measureMessageList) {
            if (measure.getMetricId() == 23) {
                assertFalse(firstMessageParsed);
                assertTrue(measure.getTime() == 6548);
                assertTrue(measure.hasDoubleValue());
                assertTrue(measure.getDoubleValue() == 1.79);


                assertFalse(measure.hasFloatValue());
                assertFalse(measure.hasBooleanValue());
                assertFalse(measure.hasLongValue());
                assertFalse(measure.hasIntValue());

                firstMessageParsed = true;
            }
            else if (measure.getMetricId() == 7) {
                assertFalse(secondMessageParsed);
                assertTrue(measure.getTime() == 6548);
                assertTrue(measure.hasFloatValue());
                assertTrue(measure.getFloatValue() == 789f);

                assertFalse(measure.hasDoubleValue());
                assertFalse(measure.hasBooleanValue());
                assertFalse(measure.hasLongValue());
                assertFalse(measure.hasIntValue());

                secondMessageParsed = true;
            }
            else if (measure.getMetricId() == 5) {
                assertFalse(thirdMessageParsed);
                assertTrue(measure.getTime() == 85);
                assertTrue(measure.hasBooleanValue());
                assertTrue(measure.getBooleanValue() == true);

                assertFalse(measure.hasDoubleValue());
                assertFalse(measure.hasFloatValue());
                assertFalse(measure.hasLongValue());
                assertFalse(measure.hasIntValue());

                thirdMessageParsed = true;
            }
            else if (measure.getMetricId() == 78) {
                assertFalse(forthMessageParsed);
                assertTrue(measure.getTime() == 659);
                assertTrue(measure.hasLongValue());
                assertTrue(measure.getLongValue() == 765);

                assertFalse(measure.hasDoubleValue());
                assertFalse(measure.hasFloatValue());
                assertFalse(measure.hasBooleanValue());
                assertFalse(measure.hasIntValue());

                forthMessageParsed = true;
            }
            else if (measure.getMetricId() == 123) {
                assertFalse(fifthMessageParsed);
                assertTrue(measure.getTime() == 659);
                assertTrue(measure.hasIntValue());
                assertTrue(measure.getIntValue() == 134);

                assertFalse(measure.hasDoubleValue());
                assertFalse(measure.hasFloatValue());
                assertFalse(measure.hasBooleanValue());
                assertFalse(measure.hasLongValue());

                fifthMessageParsed = true;
            }
        }
        assertTrue(firstMessageParsed);
        assertTrue(secondMessageParsed);
        assertTrue(thirdMessageParsed);
        assertTrue(forthMessageParsed);
        assertTrue(fifthMessageParsed);
    }
}