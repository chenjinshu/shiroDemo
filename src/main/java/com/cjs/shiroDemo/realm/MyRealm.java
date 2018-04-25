package com.cjs.shiroDemo.realm;

import org.apache.logging.log4j.spi.LoggerAdapter;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm implements Realm {

    private Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Override
    public String getName() {
        return "MyRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
       return authenticationToken instanceof UsernamePasswordToken; //仅支持UsernamePasswordToken类型的Token
       // return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();  //得到用户名
        String password = new String((char[])authenticationToken.getCredentials()); //得到密码
        logger.info("用户名：" + username);
        logger.info("密码：" + password);

        if("lq".equals(username) && "liqing".equals(password)) {
            //如果身份认证验证成功，返回一个AuthenticationInfo实现；
            return new SimpleAuthenticationInfo(username, password, getName());
        }
        return null;
    }

}
