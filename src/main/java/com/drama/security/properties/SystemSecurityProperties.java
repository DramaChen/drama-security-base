package com.drama.security.properties;

import com.drama.security.properties.login.LoginProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "drama.security")
public class SystemSecurityProperties {

    public SystemSecurityProperties(){
        System.out.println("初始化");
    }

    LoginProperties login = new LoginProperties();

    public LoginProperties getLogin() {
        return login;
    }

    public void setLogin(LoginProperties login) {
        this.login = login;
    }
}
