package com.drama.security.properties.social;

import com.drama.security.security.social.qq.api.QQ;
import lombok.Data;

@Data
public class SocialSecurityProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl;
}
