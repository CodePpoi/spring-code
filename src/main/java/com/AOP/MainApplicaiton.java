package com.AOP;

import com.AOP.biz.BizA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WangHe
 * @Date: 2020/7/6 16:57
 */
@SpringBootApplication
@RestController
public class MainApplicaiton {

    @Autowired
    BizA bizA;

    @GetMapping("/")
    public String test() {
        bizA.doSomething();
        return "ttt";
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplicaiton.class, args);
    }
}
