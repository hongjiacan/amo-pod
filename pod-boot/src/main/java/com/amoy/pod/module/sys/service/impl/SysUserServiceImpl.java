package com.amoy.pod.module.sys.service.impl;

import com.amoy.pod.module.sys.domain.SysUserEntity;
import com.amoy.pod.module.sys.mapper.SysUserMapper;
import com.amoy.pod.module.sys.service.SysUserService;
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
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据userName查找用户
     * @param userName
     * @return
     */
    public SysUserEntity queryByUserName(String userName){

        return sysUserMapper.queryByUserName(userName);
    }
}
