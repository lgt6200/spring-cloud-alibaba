package com.lumlord.service;

import com.lumlord.dao.ProductDao;
import com.lumlord.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
