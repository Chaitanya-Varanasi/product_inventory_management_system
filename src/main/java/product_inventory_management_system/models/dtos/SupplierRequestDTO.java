package product_inventory_management_system.models.dtos;

import product_inventory_management_system.models.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SupplierRequestDTO {
    @NotBlank(message = "Supplier name should not be blank!")
    private String name;
    public SupplierRequestDTO(Supplier supplier) {
        this.name = supplier.getName();
    }
}
