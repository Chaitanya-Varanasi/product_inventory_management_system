package product_inventory_management_system.models.dtos;

import product_inventory_management_system.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private Integer supplierId;
    private Float price;
    private String thumbnail;

    public ProductResponseDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.supplierId = product.getSupplierId();
        this.price = product.getPrice();
        this.thumbnail = product.getThumbnail();
    }

}
