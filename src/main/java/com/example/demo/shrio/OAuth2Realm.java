package com.example.demo.shrio;

        import com.example.demo.entity.User;
        import com.example.demo.service.UserService;
        import org.apache.shiro.SecurityUtils;
        import org.apache.shiro.authc.*;
        import org.apache.shiro.authz.AuthorizationInfo;
        import org.apache.shiro.authz.SimpleAuthorizationInfo;
        import org.apache.shiro.realm.AuthorizingRealm;
        import org.apache.shiro.subject.PrincipalCollection;
        import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by demo on 2019/8/3 - 9:55
 * version:1.0.0
 */
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 执行 授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        //获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //给资源授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(user.getUsername());
        return simpleAuthorizationInfo;

    }

    /**
     * 执行 认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从数据库中获取用户数据,这里的 token.getUsername 指向 username
        System.out.println("token.getUsername()的值：" + token.getUsername());
        User user = userService.findUserByUsername(token.getUsername());
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        if (user == null) {
            return null;//直接返回shiro底层中的UnknownAccountException
        }
        //判断密码
        System.out.println("token保存的密码：" + token.getPassword());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
    }
}
