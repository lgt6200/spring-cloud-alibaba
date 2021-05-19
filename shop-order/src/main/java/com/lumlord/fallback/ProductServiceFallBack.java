package com.lumlord.fallback;

import com.lumlord.model.Product;
import com.lumlord.rpc.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        product.setPname("下单进入容错类");
        return product;
    }
}
