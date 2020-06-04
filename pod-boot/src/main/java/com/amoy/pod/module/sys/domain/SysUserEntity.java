package com.amoy.pod.module.sys.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
@Data
public class SysUserEntity implements Serializable {

    private long userId;
    private String userName;
    private String phone;
    private String email;
    private String openid;
    private String nickname;
    private String realname;
    private String idcard;
    private String password;
    private String birthday;
    private String gender;
    private String avatar;
    private Date createTime;
}
