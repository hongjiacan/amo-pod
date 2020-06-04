package com.amoy.pod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/1
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
