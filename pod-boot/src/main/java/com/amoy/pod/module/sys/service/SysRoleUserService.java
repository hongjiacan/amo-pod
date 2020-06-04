package com.amoy.pod.module.sys.service;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
public interface SysRoleUserService {

    /**
     * 用户是否拥有某角色
     * @param userId 用户id
     * @param roleCode 角色编码
     * @return
     */
    public boolean hasRole(long userId, String roleCode);
}
