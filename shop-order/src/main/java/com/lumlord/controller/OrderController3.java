package com.lumlord.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lumlord.service.OrderService;
import com.lumlord.service.OrderServiceImpl3;
import com.lumlord.service.OrderServiceImpl4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController3 {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;
    @Autowired
    private OrderServiceImpl4 orderServiceImpl4;

    @RequestMapping("/order/message1")
    public String message1() throws InterruptedException {
        Thread.sleep(2000);
        orderService.message();
        return "message1";
    }
    int i = 0;
    @RequestMapping("/order/message2")
    public String message2() throws InterruptedException {
        //orderService.message();
        i++;
        if(i%3 == 0){
            throw  new RuntimeException();
        }
        return "message2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    //注意这里必须使用这个注解标识,热点规则不生效
    public String message3(String name, Integer age) {
        return name + age;
    }

    @RequestMapping("/order/message4")
    //注意这里必须使用这个注解标识,热点规则不生效
    public String message4(String serviceName) {
        return serviceName;
    }

    @RequestMapping("/order/message5")
    //注意这里必须使用这个注解标识,热点规则不生效
    public String message5() {
        return orderServiceImpl3.message();
    }

    @RequestMapping("/order/message6")
    //注意这里必须使用这个注解标识,热点规则不生效
    public String message6() {
        return orderServiceImpl4.message();
    }
}
