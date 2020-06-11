package com.amoy.pod.support.security;

import com.amoy.pod.module.sys.domain.SysUserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/4
 */
@Data
public class MyUserDetails implements UserDetails {

    private SysUserEntity sysUserEntity;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(SysUserEntity sysUserEntity, Collection<? extends GrantedAuthority> authorities) {
        this.sysUserEntity = sysUserEntity;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUserEntity.getUserName();
    }

    /**
     * 账户是否未过期,过期无法验证
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户是否解锁,锁定的用户无法进行身份验证
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否已过期的用户的凭据(密码),过期的凭据防止认证
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
