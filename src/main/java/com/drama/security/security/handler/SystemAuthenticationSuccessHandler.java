package com.drama.security.security.handler;

import cn.hutool.json.JSONUtil;
import com.drama.security.constants.SecurityLoginType;
import com.drama.security.entity.vo.JsonResultVO;
import com.drama.security.properties.SystemSecurityProperties;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 *    描述：登录成功处理器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/18 8:27
 */
@Component("systemAuthenticationSuccessHandler")
public class SystemAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private SystemSecurityProperties systemSecurityProperties;

    public SystemAuthenticationSuccessHandler(SystemSecurityProperties systemSecurityProperties){
        //配置登陆成功页面
        this.systemSecurityProperties=systemSecurityProperties;
        setDefaultTargetUrl(systemSecurityProperties.getLogin().getLoginSuccessPage());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (SecurityLoginType.JSON.equals(systemSecurityProperties.getLogin().getMethod())){
            //以json形式返回用户认证信息
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(JSONUtil.toJsonStr(JsonResultVO.success("登录成功",authentication)));
        }else {
            //跳转到登陆成功页面
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
