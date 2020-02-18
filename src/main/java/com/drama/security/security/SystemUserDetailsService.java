package com.drama.security.security;

import com.drama.security.security.model.SystemUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//使该服务类作为组件
@Component
public class SystemUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 创建一个用户信息用来模拟保存一个新用户信息
        //对用户保存的密码进行加密后再保存到数据库
        String password=passwordEncoder.encode("123456");
        SystemUserDetail userDetail = new SystemUserDetail("1", username, password, true, true, false, true);
        //2. 保存用户信息后，再从数据库获取用户信息出来，并返回
        return new User(username,password,userDetail.getAuthorities());
    }
}
