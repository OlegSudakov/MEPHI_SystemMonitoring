package com.sysmon.util.netty;

import io.netty.handler.logging.LogLevel;
import org.apache.log4j.Level;

import java.util.function.Function;

public class Log4jToNettyLogLevelConverter implements Function<Level, LogLevel>
{
    @Override
    public LogLevel apply(Level level)
    {
        if (Level.ALL.equals(level) || Level.TRACE.equals(level))
            return LogLevel.TRACE;
        if (Level.DEBUG.equals(level))
            return LogLevel.DEBUG;
        if (Level.INFO.equals(level))
            return LogLevel.INFO;
        if (Level.WARN.equals(level))
            return LogLevel.WARN;
        if (Level.ERROR.equals(level) || Level.FATAL.equals(level))
            return LogLevel.ERROR;
        return null;
    }
}
