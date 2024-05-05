package product_inventory_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductInventoryManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(ProductInventoryManagementSystem.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins(CorsConfiguration.ALL).allowedMethods("*");
//            }
//        };
//    }
}
