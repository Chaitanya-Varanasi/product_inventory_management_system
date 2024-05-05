package product_inventory_management_system.repositories;

import product_inventory_management_system.models.Product;
import product_inventory_management_system.models.dtos.ProductResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String productName);

    List<ProductResponseDTO> filterProducts(Integer supplierId, Double minPrice, Double maxPrice);
}
