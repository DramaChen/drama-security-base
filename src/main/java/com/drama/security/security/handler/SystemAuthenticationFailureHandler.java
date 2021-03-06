package com.drama.security.security.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.drama.security.constants.SecurityLoginType;
import com.drama.security.entity.vo.JsonResultVO;
import com.drama.security.properties.SystemSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <pre>
 *    描述：登陆失败处理器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/18 8:46
 */
@Component("systemAuthenticationFailureHandler")
public class SystemAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private SystemSecurityProperties systemSecurityProperties;

    public SystemAuthenticationFailureHandler(SystemSecurityProperties systemSecurityProperties){
        //配置登录失败页面
        this.systemSecurityProperties=systemSecurityProperties;
        String loginFailurePage = systemSecurityProperties.getLogin().getLoginFailurePage();
        if (StrUtil.isNotBlank(loginFailurePage)){
            setDefaultFailureUrl(loginFailurePage);
        }
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (SecurityLoginType.JSON.equals(systemSecurityProperties.getLogin().getMethod())){
            //以Json形式返回错误信息
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr(JsonResultVO.error(exception.getMessage())));
        }else {
            //跳转登录失败页面
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}
