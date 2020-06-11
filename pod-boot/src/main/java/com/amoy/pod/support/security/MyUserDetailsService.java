package com.amoy.pod.support.security;

import com.amoy.pod.module.sys.domain.SysUserEntity;
import com.amoy.pod.module.sys.service.SysResourceService;
import com.amoy.pod.module.sys.service.SysRoleService;
import com.amoy.pod.module.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/4
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUserEntity userEntity = sysUserService.queryByUserName(username);
        if(userEntity == null){
            log.info("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("用户名不存在!");
        }
        Set<String> authorities = new HashSet<>();
        List<String> roleCodes = sysRoleService.queryRoleCodeListByUserId(userEntity.getUserId());
        List<String> permissions = sysResourceService.queryPermissionListByUserId(userEntity.getUserId());
        //将用户的角色和权限都添加到集合
        //权限控制语法约定如下：
        //判断是否有角色（会自动加上 ROLE_ 前缀）
        // @PreAuthorize("hasRole('ADMIN')")
        // @PreAuthorize("hasAnyRole('ADMIN1','ADMIN2')")
        //判断是否有权限
        // @PreAuthorize("hasAuthority('AUTH_PERMISSION_1')")
        // @PreAuthorize("hasAnyAuthority('AUTH_PERMISSION_1','AUTH_PERMISSION_2')")
        authorities.addAll(roleCodes);
        authorities.addAll(permissions);

        return new MyUserDetails(userEntity,
                AuthorityUtils.createAuthorityList(authorities.toArray(new String[0])));
    }
}
