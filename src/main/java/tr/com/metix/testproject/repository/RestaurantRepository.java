
package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    List<Restaurant> findAllByUser_Id(Long ids); // currentUserın sahıp oldugu tum restauranların lıstesı
}
