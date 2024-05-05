package product_inventory_management_system.services;

import product_inventory_management_system.models.Product;
import product_inventory_management_system.models.dtos.ProductFilterDTO;
import product_inventory_management_system.models.dtos.ProductRequestDTO;
import product_inventory_management_system.models.dtos.ProductResponseDTO;
import product_inventory_management_system.repositories.SupplierRepository;
import product_inventory_management_system.repositories.ProductRepository;
import product_inventory_management_system.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Value("${images_location}")
    private String FILE_PATH;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    /**
     * Registers a new product to the database
     * @param product RequestBody Product object
     * @return Product
     */
    @Override
    public ProductRequestDTO addProduct(ProductRequestDTO product){
        if(productRepository.findByName(product.getName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if(product.getName() == null || product.getPrice() == null || product.getSupplierId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Product prod = new Product();
        prod.setName(product.getName());
        prod.setPrice(product.getPrice());
        prod.setSupplierId(product.getSupplierId());
        prod.setThumbnail("default.png");
        prod.setQuantity(0);

        return new ProductRequestDTO(productRepository.save(prod));
    }

    /**
     * Gets a specified product by id
     *
     * @param id PathVariable id of the product
     * @return Product
     */
    @Override
    public ProductResponseDTO getProductById(Integer id){
        if(productRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ProductResponseDTO(productRepository.findById(id).get());
    }

    /**
     * Deletes a specified product by id
     * @param id PathVariable id of the product
     */
    @Override
    public void deleteProductById(Integer id){
        if(productRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    /**
     * Updates a specified product by id
     * @param id PathVariable id of the product
     * @param productNew RequestBody new product
     * @return Product
     */
    @Override
    public ProductRequestDTO updateProductById(Integer id, ProductRequestDTO productNew){
        if(productNew.getName() == null || productNew.getPrice() == null || productNew.getSupplierId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if(productRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Product product = productRepository.findById(id).get();
        product.setName(productNew.getName());
        product.setSupplierId(productNew.getSupplierId());
        product.setPrice(productNew.getPrice());

        return new ProductRequestDTO(productRepository.save(product));
    }

    /**
     * Gets all products from the DB
     *
     * @return List of products
     */
    @Override
    public List<ProductResponseDTO> getAllProducts(){
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        productRepository.findAll().forEach(product -> productResponseDTOS.add(new ProductResponseDTO(product)));
        return productResponseDTOS;
    }


    /**
     * Upload thumbnail to a specified product
     * @param id PathVariable id
     * @param file RequestBody MultipartFile
     */
    @Override
    public void uploadPictureToProduct(Integer id, MultipartFile file) throws IOException {

        if(productRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(file == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(file.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Product product = productRepository.findById(id).get();

        String ext = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];

        // Thumbnail folder
        File p = new File(FILE_PATH);

        if(!p.exists()){
            p.mkdirs();
        }
        File f = new File(FILE_PATH + product.getThumbnail());

        if(f.delete() && !f.getName().equals("default.png")){
            System.out.println(product.getThumbnail() + " image deleted");
        }

        product.setThumbnail(product.getName() + "." + ext);

        file.transferTo(new File(FILE_PATH + product.getName() + "." + ext));

        productRepository.save(product);
    }

    /**
     * Get the thumbnail from specified product
     * @param productId PathVariable id
     * @return Product's thumbnail
     */
    @Override
    public ResponseEntity<?> getImage(Integer productId) {
        if(productRepository.findById(productId).isPresent()){
            Product product = productRepository.findById(productId).get();
            byte[] d;
            try {
                d = Files.readAllBytes(new File(FILE_PATH + product.getThumbnail()).toPath());
                MediaType m = product.getThumbnail().split("\\.")[1].equals("jpg") ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
                return ResponseEntity.status(HttpStatus.OK).contentType(m).body(d);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public List<ProductResponseDTO> filterProducts(ProductFilterDTO filter) {
        return productRepository.filterProducts(filter.getSupplierId(), filter.getMinPrice(), filter.getMaxPrice());
    }

}
