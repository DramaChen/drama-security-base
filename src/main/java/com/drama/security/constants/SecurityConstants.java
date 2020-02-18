package com.drama.security.constants;

public interface SecurityConstants {

    /**
     * <pre>
     *     描述：默认登录跳转页面
     * </pre>
     */
    String LOGIN_PAGE="/loginPage.html";
    /**
     * <pre>
     *     描述：账号密码登录表单提交路径
     * </pre>
     */
    String LOGIN_PROCESS_URL="/authentication/login";
    /**
     * <pre>
     *     描述：手机登录提交路径
     * </pre>
     */
    String LOGIN_PROCESS_URL_MOBILE="/authentication/mobile";
    /**
     * <pre>
     *     描述：当请求需要身份认证时，默认跳转url
     * </pre>
     */
    String DEFAULT_UNAUTHENTICATION_URL="/security/require";
}
