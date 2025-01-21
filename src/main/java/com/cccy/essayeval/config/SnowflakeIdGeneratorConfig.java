package com.cccy.essayeval.config;

import com.cccy.essayeval.utils.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeIdGeneratorConfig {

    @Value("${snowflake.datacenterId}")
    private long datacenterId;

    @Value("${snowflake.machineId}")
    private long machineId;

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator() {
        return new SnowflakeIdGenerator(datacenterId, machineId);
    }

}
