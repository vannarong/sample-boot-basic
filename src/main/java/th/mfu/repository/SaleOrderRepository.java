package th.mfu.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.SaleOrder;

public interface SaleOrderRepository extends CrudRepository<SaleOrder, Long> {
    public List<SaleOrder> findAll();

    public Collection<SaleOrder> findByCustomerId(Long customerId);
    public List<SaleOrder> findByCustomerName(String name);
}
