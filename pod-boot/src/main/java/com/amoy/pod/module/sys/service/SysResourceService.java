package com.amoy.pod.module.sys.service;

import com.amoy.pod.module.sys.domain.SysResourceEntity;

import java.util.List;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
public interface SysResourceService {

    /**
     * 查询功能项权限列表
     * @return
     */
    public List<SysResourceEntity> queryAuthItemList();

    /**
     * 根据parentId查询子列表
     * @return
     */
    public List<SysResourceEntity> queryListByParentId(long parentId);

    /**
     * 根据用户id查找资源
     * @param userId
     * @return
     */
    public List<SysResourceEntity> queryListByUserId(long userId);

    /**
     * 根据用户id查找功能项编码列表
     * @param userId
     * @return
     */
    public List<String> queryPermissionListByUserId(long userId);

    /**
     * 根据用户id查找菜单树
     * @param userId
     * @return
     */
    public List<SysResourceEntity> queryUserMenus(long userId);
}
