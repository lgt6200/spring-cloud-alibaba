package com.lumlord.controller;

import com.alibaba.fastjson.JSON;
import com.lumlord.model.Order;
import com.lumlord.model.Product;
import com.lumlord.rpc.ProductService;
import com.lumlord.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductService productService;

    //准备买1件商品
    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");
        //feign调用
        Product product = productService.findByPid(pid);
        if (product.getPid() == -1) {
            Order order = new Order();
            order.setPname("下单失败");
            return order;
        }
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);
        return order;
    }


    /*  从nacos中获取服务地址
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        int index = new Random().nextInt(instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();*/


    /*   String url = "service-product";
        通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject( "http://" + url + "/product/" + pid, Product.class);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));*/
}
