package com.microservice.productService;


import com.microservice.productService.payload.request.ProductRequest;
import com.microservice.productService.service.ProductService;
import com.microservice.productService.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenProductObject_whenCreateProduct_thenReturnSavedProduct() throws Exception{

        ProductRequest product=ProductRequest.builder()
                .name("Mobile")
                .price(20000)
                .quantity(10)
                .build();

        productService.addProduct(product);

        ResultActions response=mockMvc.perform(post("/product/add"));

        response.andDo(print()).
                andExpect(status().isCreated());
                //.andExpect()

    }
}
