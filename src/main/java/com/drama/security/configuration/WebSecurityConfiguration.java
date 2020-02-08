package com.drama.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //表单登录
                //指定自定义登录页面
                .loginPage("/loginPage.html")
                //配置登录接口路径，需要与页面form表单的action保持一致
                .loginProcessingUrl("/authentication/login")
             .and()
                .authorizeRequests()
                //指定连接，放行对登录页面路径的拦截
                .antMatchers("/loginPage.html").permitAll()
                .anyRequest() //拦截所有请求
                .authenticated()//都需要进行认证
                .and()
                //关闭跨域
                .csrf().disable();
    }
}
