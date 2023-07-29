package com.microservice.productService.service;

import com.microservice.productService.payload.request.ProductRequest;
import com.microservice.productService.payload.response.ProductResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId,long quantity);

    public void deleteProductById(long productId);

 //   String addUser(UserInfo userInfo);

    List<ProductResponse> getAllProducts();

    void getProductByName(Map<String, ProductResponse> keySet);
}
