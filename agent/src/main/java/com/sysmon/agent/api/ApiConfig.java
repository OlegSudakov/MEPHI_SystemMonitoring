package com.sysmon.agent.api;

import com.sysmon.agent.api.tcpserver.TcpServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        TcpServerConfig.class
})
public class ApiConfig
{
}
