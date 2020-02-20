package com.drama.security.configuration;

import com.drama.security.properties.SystemSecurityProperties;
import com.drama.security.security.session.SystemExpiredSessionStrategy;
import com.drama.security.security.session.SystemInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class SecurityCommonConfig {

    @Autowired
    private SystemSecurityProperties properties;

    @Bean
    public  SessionInformationExpiredStrategy informationExpiredStrategy(){
        return new SystemExpiredSessionStrategy(properties.getLogin().getSessionInvalidUrl());
    };

    @Bean
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new SystemInvalidSessionStrategy(properties.getLogin().getSessionInvalidUrl());
    };
}
