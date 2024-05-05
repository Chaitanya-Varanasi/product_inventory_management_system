package product_inventory_management_system.models.dtos;

import product_inventory_management_system.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDTO {
    private Integer id;
    @NotBlank(message = "Product's name should not be blank.")
    private String name;

    @NotBlank(message = "Product's supplierId should not be blank.")
    private Integer supplierId;
    @NotNull(message = "Product's price should not be null.")
    @Min(1)
    private Float price;

    public ProductRequestDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.supplierId = product.getSupplierId();
        this.price = product.getPrice();
    }
}
