package tr.com.metix.testproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.Restaurant;
import tr.com.metix.testproject.domain.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {

    Optional<Stock> findAllByProducts_Id(Long id);
}
