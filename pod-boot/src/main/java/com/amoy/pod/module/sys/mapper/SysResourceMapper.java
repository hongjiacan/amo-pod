package com.amoy.pod.module.sys.mapper;

import com.amoy.pod.module.sys.domain.SysResourceEntity;
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
public interface SysResourceMapper extends BaseMapper<SysResourceEntity> {

    List<SysResourceEntity> queryAllList();

    List<SysResourceEntity> queryAuthItemList();

    List<SysResourceEntity> queryListByParentId(@Param("parentId")long parentId);

    List<SysResourceEntity> queryListByUserId(@Param("userId")long userId);
}
