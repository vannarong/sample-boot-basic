package th.mfu;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface productRepository extends CrudRepository<product, Long>{
    List<product> findAll();
}
