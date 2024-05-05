package product_inventory_management_system.models.dtos;

import product_inventory_management_system.models.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SupplierResponseDTO {
    private Integer id;
    private String name;

    public SupplierResponseDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
    }
}
