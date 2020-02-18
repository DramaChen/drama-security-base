package com.drama.security.configuration;

import com.drama.security.constants.SecurityConstants;
import com.drama.security.properties.SystemSecurityProperties;
import com.drama.security.security.handler.SystemAuthenticationFailureHandler;
import com.drama.security.security.handler.SystemAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfiguration extends AbstractSecurityConfiguration {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http.authorizeRequests()
                //指定连接，放行对登录页面路径的拦截
                .antMatchers(systemSecurityProperties.getLogin().getLoginPage(),
                        systemSecurityProperties.getLogin().getLoginProcessUrl(),
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL).permitAll()
                .anyRequest() //拦截所有请求
                .authenticated()//都需要进行认证
                .and()
                //关闭跨域
                .csrf().disable();
    }
}
