package com.crave.edu.shiro;

import com.crave.edu.bean.Users;
import com.crave.edu.bean.UsersExample;
import com.crave.edu.mapper.UsersDAO;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 自定义 Realm
 * @Date 2018-03-25
 * @Time 21:46
 */
@Component
public class CustomRealm extends AuthorizingRealm {
    private final UsersDAO usersDAO;

    @Autowired
    public CustomRealm(UsersDAO parkMapper) {
        this.usersDAO = parkMapper;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String((char[]) token.getCredentials());
        if (StringUtils.isBlank(username)){
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)){
            throw new AccountException("密码不能为空");
        }

        // 从数据库获取对应用户名密码的用户
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(username);
        List<Users> users = usersDAO.selectByExample(example);
        String userPassword = null;
        Byte userState = null;
        if (users != null && users.size() > 0){
            Users park = users.get(0);
            userPassword = park.getPassword();
            userState = park.getStatus();
        }else{
            throw new AccountException("用户不存在");
        }
        if (!userPassword.equals(password)) {
            throw new AccountException("密码输入有误");
        } else if (null == userState || userState != 0){
            throw new AccountException("账号已停用");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), userPassword, getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*//获得该用户角色
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        String role = parkMapper.getRole(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);*/
        return info;
    }
}
