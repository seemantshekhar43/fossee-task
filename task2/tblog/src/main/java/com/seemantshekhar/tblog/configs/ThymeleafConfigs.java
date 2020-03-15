package com.seemantshekhar.tblog.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

/**
 * Configuration for Thymeleaf (Security)
 */
@Configuration
public class ThymeleafConfigs {

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

}