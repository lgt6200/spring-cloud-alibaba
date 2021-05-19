package com.lumlord.rpc;

import com.lumlord.fallback.ProductServiceFallBack;
import com.lumlord.fallback.ProductServiceFallBackFactory;
import com.lumlord.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "service-product"
        //fallback = ProductServiceFallBack.class
        // fallbackFactory = ProductServiceFallBackFactory.class
)
public interface ProductService {

    @PostMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
