package com.alireza.sadeghi.springboot.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "taco.order")
@Data
public class TacoProps {

    private int pageSize=20;
}
