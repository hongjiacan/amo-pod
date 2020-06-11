package com.amoy.pod.module.sys.web;

import com.amoy.pod.support.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/3
 */
@Api("测试controller")
@RestController
public class HelloController {

    @ApiOperation(value = "sayHello", httpMethod = "POST")
    @RequestMapping(value = "/sayHello", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('AUTH_SAYHELLO')")
    public Result sayHello(@RequestParam("name")String name){

        return Result.ok("hello " + name);
    }
}
