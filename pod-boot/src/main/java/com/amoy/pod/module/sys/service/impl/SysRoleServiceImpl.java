package com.amoy.pod.module.sys.service.impl;

import com.amoy.pod.module.sys.domain.SysRoleEntity;
import com.amoy.pod.module.sys.mapper.SysRoleMapper;
import com.amoy.pod.module.sys.service.SysRoleService;
import com.amoy.pod.support.base.Result;
import com.amoy.pod.support.utils.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    /**
     * 查询角色列表
     * @param params
     * @return
     */
    public List<SysRoleEntity> queryList(Map<String, Object> params){

        return sysRoleMapper.queryList(params);
    }

    /**
     * 根据角色id查找
     * @param roleId
     * @return
     */
    public SysRoleEntity queryByRoleId(long roleId){

        return sysRoleMapper.queryObject(roleId);
    }

    /**
     * 根据角色编码查询
     * @param roleCode
     * @return
     */
    public SysRoleEntity queryByRoleCode(String roleCode){

        return sysRoleMapper.queryByRoleCode(roleCode);
    }

    /**
     * 根据用户id查找角色列表
     * @param userId
     * @return
     */
    public List<SysRoleEntity> queryListByUserId(long userId){

        return sysRoleMapper.queryListByUserId(userId);
    }

    /**
     * 新增
     * @param sysRoleEntity
     * @return
     */
    public Result save(SysRoleEntity sysRoleEntity){

        long roleId = SequenceUtil.nextId();
        sysRoleEntity.setRoleId(roleId);
        int result = sysRoleMapper.save(sysRoleEntity);

        log.info("save result:"+result);

        return Result.ok("提交成功").put("roleId",roleId);
    }

    /**
     * 更新
     * @param sysRoleEntity
     * @return
     */
    public Result update(SysRoleEntity sysRoleEntity){

        int result = sysRoleMapper.update(sysRoleEntity);

        return Result.ok("提交成功");
    }
}
