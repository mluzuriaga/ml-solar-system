package com.mercadopago.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase de configuracion
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.mercadopago")
public class SolarSystemConfig {
}
