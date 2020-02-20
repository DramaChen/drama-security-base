package com.drama.security.configuration;

import com.drama.security.constants.SecurityConstants;
import com.drama.security.properties.SystemSecurityProperties;
import com.drama.security.security.session.SystemExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class SystemWebSecurityConfiguration extends AbstractSecurityConfiguration {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;//配置组件

    @Autowired
    private SpringSocialConfigurer systemSpringSocialConfigurer;//第三方登录拦截器

    @Autowired
    private SessionInformationExpiredStrategy informationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http.apply(systemSpringSocialConfigurer)
                .and()
                .sessionManagement()
                    .invalidSessionUrl(systemSecurityProperties.getLogin().getSessionInvalidUrl())
                .invalidSessionStrategy(invalidSessionStrategy)
                    .maximumSessions(systemSecurityProperties.getLogin().getMaximumSessions())//最多同时几个用户登录
                .maxSessionsPreventsLogin(systemSecurityProperties.getLogin().getMaxSessionsPreventsLogin())//不允许有其他用户继续登录
                .expiredSessionStrategy(informationExpiredStrategy)
                .and()
                .and()
                .authorizeRequests()
                //指定连接，放行对登录页面路径的拦截
                .antMatchers(systemSecurityProperties.getLogin().getSessionInvalidUrl(),
                        systemSecurityProperties.getLogin().getLoginPage(),
                        systemSecurityProperties.getLogin().getLoginProcessUrl(),
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL).permitAll()
                .anyRequest() //拦截所有请求
                .authenticated()//都需要进行认证
                .and()
                //关闭跨域
                .csrf().disable();
    }

}
