package com.example.demo.shrio;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by demo on 2019/8/3 - 10:51
 * version:1.0.0
 */
@Configuration
public class ShrioConfig {
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro内置过滤器
        /*
         * anon:表示可以匿名使用。
           authc:表示需要认证(登录)才能使用，没有参数
           roles：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
           perms：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
           rest：根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
           port：当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
           authcBasic：没有参数表示httpBasic认证
           ssl:表示安全的url请求，协议为https
           user:当登入操作时不做检查
         */
        Map<String, String> fmap = new HashMap<String, String>();
        fmap.put("/one", "authc");
        fmap.put("/two", "authc");
        fmap.put("/one", "perms[张三]");
        fmap.put("/two", "perms[李四]");
        //被拦截返回登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //授权拦截返回页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/permission");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fmap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "defaultWebSecurityManager")
    //创建DefaultWebSecurityManager
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("oAuth2Realm") OAuth2Realm oAuth2Realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(oAuth2Realm);
        return defaultWebSecurityManager;
    }

    @Bean(name = "oAuth2Realm")
    public OAuth2Realm getOAuth2Realm() {
        return new OAuth2Realm();
    }
}
