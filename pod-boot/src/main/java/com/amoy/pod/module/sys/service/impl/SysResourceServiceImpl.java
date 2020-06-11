package com.amoy.pod.module.sys.service.impl;

import com.amoy.pod.module.sys.domain.SysResourceEntity;
import com.amoy.pod.module.sys.mapper.SysResourceMapper;
import com.amoy.pod.module.sys.service.SysResourceService;
import com.amoy.pod.module.sys.service.SysRoleUserService;
import com.amoy.pod.support.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    /**
     * 查询所有资源列表
     * @return
     */
    public List<SysResourceEntity> queryAllList(){

        return sysResourceMapper.queryAllList();
    }

    /**
     * 查询功能项权限列表 type = '3'
     * @return
     */
    public List<SysResourceEntity> queryAuthItemList(){

        return sysResourceMapper.queryAuthItemList();
    }

    /**
     * 根据parentId查询子列表
     * @return
     */
    public List<SysResourceEntity> queryListByParentId(long parentId){

        return sysResourceMapper.queryListByParentId(parentId);
    }

    /**
     * 根据用户id查找资源
     * @param userId
     * @return
     */
    public List<SysResourceEntity> queryListByUserId(long userId){

        return sysResourceMapper.queryListByUserId(userId);
    }

    /**
     * 根据用户id查找功能项编码列表
     * @param userId
     * @return
     */
    public List<String> queryPermissionListByUserId(long userId){

        return sysResourceMapper.queryPermissionListByUserId(userId);
    }

    /**
     * 根据用户id查找菜单树
     * @param userId
     * @return
     */
    public List<SysResourceEntity> queryUserMenus(long userId){

        //用户所有资源项
        List<SysResourceEntity> menus = null;
        boolean isAdmin = sysRoleUserService.hasRole(userId, Constants.RoleCodeAdmin);
        if(isAdmin){
            menus = queryAllList();
        }else {
            menus = queryListByUserId(userId);
        }

        //取出资源项id
        List<Long> menuIds = menus.stream().map(SysResourceEntity::getResourceId).collect(Collectors.toList());

        //构造目录层菜单
        List<SysResourceEntity> directories = buildMenuByParentId(0, menuIds);

        //从目录层菜单开始递归构造
        List<SysResourceEntity> treeMenus = buildTreeMenus(directories, menuIds);
        return treeMenus;
    }

    /**
     * 根据上级id 和 用户拥有的资源id列表，返回下级资源列表
     * @param parentId 上级id
     * @param menuIds 用户拥有的资源id列表
     * @return
     */
    public List<SysResourceEntity> buildMenuByParentId(long parentId, List<Long> menuIds){
        //根据上级Id,查询所有下级资源
        List<SysResourceEntity> subMenus = queryListByParentId(parentId);
        List<SysResourceEntity> reMenus= new ArrayList<>();
        for (SysResourceEntity subMenu: subMenus){
            //如果下级资源在用户授权资源中,则添加
            if(menuIds.contains(subMenu.getResourceId())){
                reMenus.add(subMenu);
            }
        }
        return reMenus;
    }

    /**
     * 递归构造菜单树
     * @param resources 源菜单
     * @param menuIds 用户所有授权资源id列表
     * @return
     */
    public List<SysResourceEntity> buildTreeMenus(List<SysResourceEntity> resources, List<Long> menuIds){
        List<SysResourceEntity> treeMenus = new ArrayList<>();
        for (SysResourceEntity menu: resources){
            if(Constants.ResourceType.directory.getValue().equals(menu.getType())){
                List<SysResourceEntity> childMenus = buildMenuByParentId(menu.getResourceId(), menuIds);
                menu.setSubList(buildTreeMenus(childMenus, menuIds));
            }
            treeMenus.add(menu);
        }
        return treeMenus;
    }
}
