package com.amoy.pod.module.sys.service.impl;

import com.amoy.pod.module.sys.mapper.SysRoleUserMapper;
import com.amoy.pod.module.sys.service.SysRoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Service
@Slf4j
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 用户是否拥有某角色
     * @param userId 用户id
     * @param roleCode 角色编码
     * @return
     */
    public boolean hasRole(long userId, String roleCode){

        return (sysRoleUserMapper.queryByUserIdAndRoleCode(userId, roleCode)) != null;
    }
}
