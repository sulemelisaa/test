package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
