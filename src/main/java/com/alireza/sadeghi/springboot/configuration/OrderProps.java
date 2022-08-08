package com.alireza.sadeghi.springboot.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {

    @Max(value = 25,message = "must be between 5 and 25")
    @Min(value = 5,message = "must be between 5 and 25")
    private int pageSize =20;

}
