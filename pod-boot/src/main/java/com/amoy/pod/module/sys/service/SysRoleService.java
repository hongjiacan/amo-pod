package com.amoy.pod.module.sys.service;

import com.amoy.pod.module.sys.domain.SysRoleEntity;
import com.amoy.pod.support.base.Result;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
public interface SysRoleService {

    /**
     * 查询角色列表
     * @param params
     * @return
     */
    public List<SysRoleEntity> queryList(Map<String, Object> params);

    /**
     * 根据角色id查找
     * @param roleId
     * @return
     */
    public SysRoleEntity queryByRoleId(long roleId);

    /**
     * 根据角色编码查询
     * @param roleCode
     * @return
     */
    public SysRoleEntity queryByRoleCode(String roleCode);

    /**
     * 根据用户id查找角色编码列表
     * @param userId
     * @return
     */
    public List<String> queryRoleCodeListByUserId(long userId);

    /**
     * 新增
     * @param sysRoleEntity
     * @return
     */
    public Result save(SysRoleEntity sysRoleEntity);

    /**
     * 更新
     * @param sysRoleEntity
     * @return
     */
    public Result update(SysRoleEntity sysRoleEntity);
}
