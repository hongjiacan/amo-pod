package com.amoy.pod.module.sys.web;

import com.amoy.pod.module.sys.domain.SysUserEntity;
import com.amoy.pod.module.sys.service.SysUserService;
import com.amoy.pod.support.base.Result;
import com.amoy.pod.support.shiro.JwtTokenUtil;
import com.amoy.pod.support.utils.BCryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/4
 */
@Api("登录模块接口")
@RestController
public class LoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @ApiOperation(value = "login", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("userName")String userName,
                        @RequestParam("password")String password){

        SysUserEntity user = sysUserService.queryByUserName(userName);
        if(user == null){
            return Result.error("用户名不存在");
        }
        if(!BCryptUtil.check(user.getPassword(), password)){
            return Result.error("用户名或密码错误");
        }
        String token = jwtTokenUtil.generateToken(user);
        return Result.ok().put(jwtTokenUtil.getHeader(), token);
    }
}
