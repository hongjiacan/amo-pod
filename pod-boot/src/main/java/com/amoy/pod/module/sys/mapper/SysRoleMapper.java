package com.amoy.pod.module.sys.mapper;

import com.amoy.pod.module.sys.domain.SysRoleEntity;
import com.amoy.pod.support.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    SysRoleEntity queryByRoleCode(@Param("roleCode")String roleCode);

    List<String> queryRoleCodeListByUserId(@Param("userId")long userId);
}
