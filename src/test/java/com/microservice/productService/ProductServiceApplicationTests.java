package com.microservice.productService;

import com.microservice.productService.entity.Product;
import com.microservice.productService.repository.ProductRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests{

	@Test
	void contextLoads() {
	}


	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenProducts_whenGetAllProducts_thenListOfProducts() throws Exception{

		List<Product> products=
				List.of(Product.builder().productId(1).productName("Laptop").quantity(20).price(1000).build(),
						Product.builder().productId(2).productName("Charger").quantity(10).price(20000).build());
		productRepository.saveAll(products);


		ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/product/all"));


		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(products.size())));
	}

}
