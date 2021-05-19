package com.lumlord.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lumlord.dao.OrderDao;
import com.lumlord.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public void save(Order order) {
        orderDao.save(order);
    }

    //@SentinelResource("message")
    public void message() throws InterruptedException {
        System.out.println("message");
    }
}
