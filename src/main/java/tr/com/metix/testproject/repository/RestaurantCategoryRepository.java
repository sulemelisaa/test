
package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.RestaurantCategory;

import java.util.List;
import java.util.Optional;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory,Long> {

    List<RestaurantCategory> findAllByRestaurant_IdIn(List<Long> ids);
    Optional<RestaurantCategory> findAllByRestaurant_Id(Long id);
}
