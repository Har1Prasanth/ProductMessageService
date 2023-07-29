package com.microservice.productService.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

//@Builder

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@Builder
public class ProductRequest {

    private String name;
    private long price;
    private long quantity;


}
