package com.amoy.pod.module.sys.domain;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Data
public class SysResourceEntity {

    private long resourceId;
    private long parentId;
    private String name;
    private String url;
    private String icon;
    private Integer sort;
    private String permission;
    private String remark;
    private String type;

    private List subList;//子列表
}
