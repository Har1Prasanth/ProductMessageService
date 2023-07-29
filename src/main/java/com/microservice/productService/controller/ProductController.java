package com.microservice.productService.controller;

import com.microservice.productService.entity.Product;
import com.microservice.productService.entity.UserInfo;
import com.microservice.productService.payload.request.AuthRequest;
import com.microservice.productService.payload.request.ProductRequest;
import com.microservice.productService.payload.response.ProductResponse;
import com.microservice.productService.service.ExcelReporter;
//import com.microservice.productService.service.JwtService;
import com.microservice.productService.service.ProductService;
import com.microservice.productService.service.RabbitMqSender;
//import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;


    private final RabbitMqSender rabbitMqSender;


    @GetMapping(value = "/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok().body("Welcome");
    }


    @PostMapping(value = "/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) {
        log.info("ProductController | addProduct is called");
        log.info("ProductController | addProduct | Product Request : " + productRequest);

        long productId = productService.addProduct(productRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("productId: " + productId + " is added successfully");

    }

    @GetMapping(value = "/id")
    public ResponseEntity<ProductResponse> getProductById(@RequestHeader(name = "ID", required = true) long productId) {

        log.info("ProductController | getProductById is called");

        log.info("ProductController | getProductById | productId : " + productId);

        ProductResponse productResponse = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }


    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity) {

        log.info("ProductController | reduceQuantity is called");

        log.info("ProductController | reduceQuantity | productId : " + productId);
        log.info("ProductController | reduceQuantity | quantity : " + quantity);

        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public void deleteProductById(@RequestHeader(name = "Id") long productId) {

        productService.deleteProductById(productId);
    }

    @GetMapping(value = "/all")
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product.xlsx";
        response.setHeader(headerKey, headerValue);

        List<ProductResponse> listUsers = productService.getAllProducts();

        ExcelReporter excelExporter = new ExcelReporter(listUsers);

        excelExporter.export(response);
    }


    @PostMapping(value = "/payload", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getPayload(@RequestBody List<ProductResponse> productResponse) {
        Map<String, ProductResponse> responseMap = new HashMap<>();

        for (ProductResponse p : productResponse
        ) {
            responseMap.putIfAbsent(p.getProductName(), p);

        }
        productService.getProductByName(responseMap);

        log.info("DB response received now ");


    }

}
