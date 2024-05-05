package product_inventory_management_system.controllers;

import product_inventory_management_system.models.dtos.SupplierRequestDTO;
import product_inventory_management_system.models.dtos.SupplierResponseDTO;
import product_inventory_management_system.services.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@Validated
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/")
    public ResponseEntity<SupplierRequestDTO> addNewSupplier(@RequestBody @Valid SupplierRequestDTO supplier){
        return new ResponseEntity<>(supplierService.addSupplier(supplier), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<SupplierResponseDTO> getSuppliers(){
        return supplierService.getSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierResponseDTO getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplierById(@PathVariable Integer id){
        supplierService.deleteSupplierById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierRequestDTO> updateSupplierById(@PathVariable Integer id, @RequestBody @Valid SupplierRequestDTO supplier){
        return new ResponseEntity<>(supplierService.updateSupplierById(id, supplier), HttpStatus.OK);
    }
}
