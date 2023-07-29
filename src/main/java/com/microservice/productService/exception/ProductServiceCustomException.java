package com.microservice.productService.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductServiceCustomException extends RuntimeException{
    private String errorCode;
    private String message;

    public ProductServiceCustomException(String message,String errorCode){
        super(message);
        this.errorCode=errorCode;
        this.message=message;
    }

    public ProductServiceCustomException(ProductServiceCustomException e) {
        super(e.getMessage());
        this.message=e.getMessage();
        this.errorCode=e.getErrorCode();
    }
}
