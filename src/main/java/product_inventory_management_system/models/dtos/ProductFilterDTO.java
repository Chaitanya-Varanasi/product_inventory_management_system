package product_inventory_management_system.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductFilterDTO {
    private Integer supplierId;
    private Double minPrice;
    private Double maxPrice;
}
