package product_inventory_management_system.services.interfaces;

import product_inventory_management_system.models.dtos.SupplierRequestDTO;
import product_inventory_management_system.models.dtos.SupplierResponseDTO;

import java.util.List;

public interface ISupplierService {
    SupplierRequestDTO addSupplier(SupplierRequestDTO supplier);
    SupplierResponseDTO getSupplierById(Integer id);
    List<SupplierResponseDTO> getSuppliers();
    void deleteSupplierById(Integer id);
    SupplierRequestDTO updateSupplierById(Integer id, SupplierRequestDTO supplierNew);

}
