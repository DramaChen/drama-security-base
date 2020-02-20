package com.drama.security.properties;

import com.drama.security.properties.login.LoginProperties;
import com.drama.security.properties.social.SocialSecurityProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "drama.security")
@Data
public class SystemSecurityProperties {

    public LoginProperties login = new LoginProperties();

    public SocialSecurityProperties social = new SocialSecurityProperties();
}
