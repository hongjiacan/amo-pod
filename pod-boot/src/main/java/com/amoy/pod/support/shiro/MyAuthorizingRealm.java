package com.amoy.pod.support.shiro;

import com.amoy.pod.module.sys.domain.SysRoleEntity;
import com.amoy.pod.module.sys.domain.SysUserEntity;
import com.amoy.pod.module.sys.service.SysRoleService;
import com.amoy.pod.module.sys.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/4
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 权限获取
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //从 PrincipalCollection 中来获取 token
        String token = (String) principalCollection.getPrimaryPrincipal();
        String username = jwtTokenUtil.getUsernameFromToken(token);
        SysUserEntity user = sysUserService.queryByUserName(username);
        if(user != null){
            //添加角色和权限
            List<SysRoleEntity> roles = sysRoleService.queryListByUserId(user.getUserId());
            for (SysRoleEntity role : roles) {
                simpleAuthorizationInfo.addRole(role.getRoleCode());
            }
            simpleAuthorizationInfo.addStringPermission("permission");
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登陆验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取token，既前端传入的token
        String token = (String) authenticationToken.getCredentials();
        String username = jwtTokenUtil.getUsernameFromToken(token);
        SysUserEntity user = sysUserService.queryByUserName(username);
        if (user == null || !jwtTokenUtil.validateToken(token, user)){
            throw new AuthenticationException("认证失败");
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
