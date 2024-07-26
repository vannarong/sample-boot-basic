package th.mfu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import th.mfu.domain.Customer;
import th.mfu.domain.SaleOrder;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.SaleOrderDTO;
import th.mfu.dto.mapper.SaleOrderMapper;
import th.mfu.repository.CustomerRepository;
import th.mfu.repository.SaleOrderRepository;


@RestController
public class SaleOrderController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SaleOrderRepository saleOrderRepository;

    // create order by customer
    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<String> createOrder(@PathVariable Long customerId, @RequestBody SaleOrder saleOrder) {
        // check if customer exists
        if (!customerRepository.existsById(customerId)) {
            
            return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
        }
        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        // set customer to order
        saleOrder.setCustomer(optCustomer.get());
        saleOrderRepository.save(saleOrder);
        return new ResponseEntity<String>("Order created", HttpStatus.CREATED);
    }

   
    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomers(){
        List<SaleOrder> SaleOrder = saleOrderRepository.findAll();
        List<SaleOrderDTO> dtos = new ArrayList<SaleOrderDTO>();
        for(SaleOrder cust: SaleOrder){
            SaleOrderDTO dto  = new SaleOrderDTO();
            SaleOrderMapper.updateSaleOrderFromEntity(cust, dto);
            dtos.add(dto);
        }
        return new ResponseEntity<Collection>(saleOrderRepository.findAll(), HttpStatus.OK);
    }
    // // GET for getting orders by customer
    // @GetMapping("/customers/{customerId}/orders")
    // public ResponseEntity<Collection<SaleOrder>> getOrdersByCustomer(@PathVariable Long customerId) {
    //     System.out.println("getOrdersByCustomer is called");
    //     Optional<Customer> optCustomer = customerRepository.findById(customerId);
    //     if (!optCustomer.isPresent()) {
    //         return new ResponseEntity<Collection<SaleOrder>>(HttpStatus.NOT_FOUND);
    //     }
    //     Collection<SaleOrder> orders = saleOrderRepository.findByCustomerId(customerId);
    //     return new ResponseEntity<Collection<SaleOrder>>(orders, HttpStatus.OK);
    // }
  // get orders by customer id
  @GetMapping("/customers/{customerId}/orders")
  public ResponseEntity<Collection<SaleOrder>> getOrderByCustomer(@PathVariable Long customerId) {
      // check if customer exists
      if (!customerRepository.existsById(customerId)) {
          return new ResponseEntity<Collection<SaleOrder>>( HttpStatus.NOT_FOUND);
      }
      Collection<SaleOrder> orders = saleOrderRepository.findByCustomerId(customerId);
      return new ResponseEntity<Collection<SaleOrder>>(orders, HttpStatus.OK);
    
}
}