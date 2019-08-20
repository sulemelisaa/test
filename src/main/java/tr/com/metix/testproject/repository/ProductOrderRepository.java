package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Long> {
}
