package com.lumlord.fallback;

import com.lumlord.model.Product;
import com.lumlord.rpc.ProductService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
//可以看到报错信息
//@Component
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                throwable.printStackTrace();
                Product product = new Product();
                product.setPid(-1);
                return product;
            }
        };
    }
}