
package tr.com.metix.testproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser_Id(Long id);
}
