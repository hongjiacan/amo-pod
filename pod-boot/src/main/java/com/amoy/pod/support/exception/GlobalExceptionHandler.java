package com.amoy.pod.support.exception;

import com.amoy.pod.support.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/4
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Result exceptionHandler(HttpServletResponse response, Exception e) {
//        log.error(e.getMessage(), e);
//        if(response.getStatus() == HttpStatus.OK.value()){
//            response.setStatus(HttpStatus.BAD_REQUEST.value());
//        }
//        return Result.error(e.getMessage(), String.valueOf(response.getStatus()));
//    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Result.error(e.getMessage()), HttpStatus.OK);
    }
}
