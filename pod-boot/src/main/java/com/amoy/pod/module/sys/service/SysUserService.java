package com.amoy.pod.module.sys.service;

import com.amoy.pod.module.sys.domain.SysUserEntity;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
public interface SysUserService {

    /**
     * 根据userName查找用户
     * @param userName
     * @return
     */
    public SysUserEntity queryByUserName(String userName);
}
