package th.mfu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Customer;
import th.mfu.domain.product;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.productDTO;
import th.mfu.dto.mapper.CustomerMapper;
import th.mfu.dto.mapper.productMapper;
import th.mfu.repository.productRepository;



@RestController
public class productController {

  @Autowired
  productMapper custMapper;

  @Autowired
  private productRepository productRepo; // Replace with your actual ProductRepository interface

  // GET for a product
  @GetMapping("/products/{id}")
  public ResponseEntity<product> getProduct(@PathVariable Long id) {
    Optional<product> product = productRepo.findById(id);
    if (!product.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(product.get(), HttpStatus.OK);
  }

  // Get all products
    @GetMapping("/product")
    public ResponseEntity<Collection> getAllproduct(){
        List<product> product = productRepo.findAll();
        List<productDTO> dtos = new ArrayList<productDTO>();
        for(product cust: product){
          productDTO dto  = new productDTO();
            custMapper.updateproductFromEntity(cust, dto);
            dtos.add(dto);
        }
        return new ResponseEntity<Collection>(productRepo.findAll(), HttpStatus.OK);
    }

    
    //PATCH for updating products
    @PatchMapping("/products/{id}")
    public ResponseEntity<String> updateproductDTO(@PathVariable Long id,@RequestBody productDTO productDTO){
        if(!productRepo.existsById(id))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        Optional<product> productEnt = productRepo.findById(id);
        product product = productEnt.get();
        custMapper.updateproductFromDto(productDTO, product);
        productRepo.save(product);
        return new ResponseEntity<String>("product updated", HttpStatus.OK);
    }

  // POST for creating a products
  @PostMapping("/products")
  public ResponseEntity<String> createProduct(@RequestBody product product) {
    productRepo.save(product);
    return new ResponseEntity<>("Product created", HttpStatus.CREATED);
  }

  // DELETE for deleting a product by id
  @DeleteMapping("/products/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    productRepo.deleteById(id);
    return new ResponseEntity<>("Product deleted", HttpStatus.NO_CONTENT);
  }
}
