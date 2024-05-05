package product_inventory_management_system.controllers;

import product_inventory_management_system.models.dtos.ProductFilterDTO;
import product_inventory_management_system.models.dtos.ProductRequestDTO;
import product_inventory_management_system.models.dtos.ProductResponseDTO;
import product_inventory_management_system.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductRequestDTO> addNewProduct(@RequestBody @Valid ProductRequestDTO product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRequestDTO> updateProductById(@PathVariable Integer id, @RequestBody @Valid ProductRequestDTO product){
        return new ResponseEntity<>(productService.updateProductById(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
    }

    @GetMapping("/")
    public List<ProductResponseDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/filter")
    public List<ProductResponseDTO> filterProducts(@Valid ProductFilterDTO filter) {
        return productService.filterProducts(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping(path =  "/thumbnail/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadPictureToProduct(@PathVariable Integer id, @RequestParam @Valid MultipartFile file) throws IOException {
        productService.uploadPictureToProduct(id, file);
    }

    @GetMapping(path = "/thumbnail/{productId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<?> getImage(@PathVariable Integer productId) {
        return productService.getImage(productId);
    }
}
