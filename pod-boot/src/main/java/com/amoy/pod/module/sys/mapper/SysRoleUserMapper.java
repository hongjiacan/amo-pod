package com.amoy.pod.module.sys.mapper;

import com.amoy.pod.module.sys.domain.SysRoleUserEntity;
import com.amoy.pod.support.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUserEntity> {

    @Select("select t1.* from sys_role_user t1 left join sys_role t2 on t2.role_id = t1.role_id " +
            " where t1.user_id = 1304297583149088 and t2.role_code = 'ROLE_ADMIN' limit 1")
    SysRoleUserEntity queryByUserIdAndRoleCode(@Param("userId")long userId, @Param("roleCode")String roleCode);

}
