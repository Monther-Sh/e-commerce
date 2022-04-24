package com.example.ecommerce;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.CustomerDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.services.CategoryServices;
import com.example.ecommerce.services.CustomerServices;
import com.example.ecommerce.services.ProductsServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(CategoryServices categoryService) {
		return args -> {
			try {
				CategoryDto instance;
				Random random = new Random(System.currentTimeMillis());
				for (int i = 0; i < 2; i++) {
					instance = new CategoryDto();
					instance.setName("default category " + random.nextInt());
					instance.setDescription("default category " + random.nextInt());
					categoryService.createCategory(instance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
	@Bean
	CommandLineRunner runner2(CustomerServices customerServices) {
		return args -> {
			try {
				CustomerDto instance;
				Random random = new Random(System.currentTimeMillis());
				for (int i = 0; i < 2; i++) {
					instance = new CustomerDto();
					instance.setName("default customer " + random.nextInt());
					instance.setEmail("default customer " + random.nextInt());
					instance.setCardNum("default customer " + random.nextInt());
					instance.setDOB(null);
					instance.setAddress("default customer " + random.nextInt());
					customerServices.createCustomer(instance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

	}
	@Bean
	CommandLineRunner runner3(ProductsServices productsServices) {
		return args -> {
			try {
				ProductDto instance;
				Random random = new Random(System.currentTimeMillis());
				for (int i = 0; i < 2; i++) {
					instance = new ProductDto();
					instance.setName("default product " + random.nextInt());
					instance.setPurchasePrice(0L);
					instance.setSellingPrice(0L);
					instance.setDescription("default product " + random.nextInt());
					instance.setExpiryDate(null);
					instance.setCategory("default product " + random.nextInt());
					productsServices.createProduct(instance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

	}
	@Bean("restTemplate")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
