package com.drama.security.properties;

import com.drama.security.properties.SystemSecurityProperties;
import com.drama.security.security.handler.SystemAuthenticationFailureHandler;
import com.drama.security.security.handler.SystemAuthenticationSuccessHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableConfigurationProperties(SystemSecurityProperties.class)
public class SecurityCoreConfig {


}
