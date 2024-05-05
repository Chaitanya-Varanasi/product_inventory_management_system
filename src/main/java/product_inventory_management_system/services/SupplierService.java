package product_inventory_management_system.services;

import product_inventory_management_system.models.Supplier;
import product_inventory_management_system.models.dtos.SupplierRequestDTO;
import product_inventory_management_system.models.dtos.SupplierResponseDTO;
import product_inventory_management_system.repositories.SupplierRepository;
import product_inventory_management_system.services.interfaces.ISupplierService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    /**
     * Adds a new supplier to the DB
     * @param supplier RequestBody supplier
     * @return Created supplier
     */
    @Override
    public SupplierRequestDTO addSupplier(SupplierRequestDTO supplier){
        if(supplier.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Supplier supplier1 = new Supplier();
        supplier1.setName(supplier.getName());

        return new SupplierRequestDTO(supplierRepository.save(supplier1));
    }

    /**
     * Gets a specified supplier by id
     *
     * @param id PathVariable supplier's id
     * @return Supplier
     */
    @Override
    public SupplierResponseDTO getSupplierById(Integer id){
        if(supplierRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new SupplierResponseDTO(supplierRepository.findById(id).get());
    }

    /**
     * Gets list of suppliers
     *
     * @return List of suppliers
     */
    @Override
    public List<SupplierResponseDTO> getSuppliers(){
        List<SupplierResponseDTO> supplierResponseDTOS = new ArrayList<>();
        supplierRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).forEach(supplier -> supplierResponseDTOS.add(new SupplierResponseDTO(supplier)));
        return supplierResponseDTOS;
    }

    /**
     * Deletes a specified supplier
     * @param id PathVariable supplier's id
     */
    @Override
    public void deleteSupplierById(Integer id){
        if(supplierRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        supplierRepository.deleteById(id);
    }

    /**
     * Updates a supplier with specified id
     * @param id PathVariable supplier's id
     * @param supplierNew RequestBody Supplier
     * @return Updated supplier
     */
    @Override
    public SupplierRequestDTO updateSupplierById(Integer id, SupplierRequestDTO supplierNew){
        if(supplierNew.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if(supplierRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Supplier supplier = supplierRepository.findById(id).get();
        supplier.setName(supplierNew.getName());

        return new SupplierRequestDTO(supplierRepository.save(supplier));
    }
}
