package com.drama.security.configuration;

import com.drama.security.constants.SecurityConstants;
import com.drama.security.properties.SystemSecurityProperties;
import com.drama.security.security.handler.SystemAuthenticationFailureHandler;
import com.drama.security.security.handler.SystemAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AbstractSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    protected AuthenticationSuccessHandler systemAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler systemAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.LOGIN_PROCESS_URL)
                .successHandler(systemAuthenticationSuccessHandler)
                .failureHandler(systemAuthenticationFailureHandler);
    }
}
