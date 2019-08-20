
package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.GeneralCategory;
import tr.com.metix.testproject.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface GeneralCategoryRepository extends JpaRepository<GeneralCategory,Long> {

    List<GeneralCategory> findAllByRestaurants_Id(Long id);
}
