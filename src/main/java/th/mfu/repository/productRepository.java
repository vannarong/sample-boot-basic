package th.mfu.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.product;
public interface productRepository extends CrudRepository<product, Long>{
    List<product> findAll();
}
