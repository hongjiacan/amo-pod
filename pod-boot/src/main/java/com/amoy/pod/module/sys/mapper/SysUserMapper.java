package com.amoy.pod.module.sys.mapper;

import com.amoy.pod.module.sys.domain.SysUserEntity;
import com.amoy.pod.support.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    SysUserEntity queryByUserName(@Param("userName")String userName);
}
