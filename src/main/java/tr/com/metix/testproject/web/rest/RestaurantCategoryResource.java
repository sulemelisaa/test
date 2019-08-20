package tr.com.metix.testproject.web.rest;

import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.service.RestaurantCategoryService;
import tr.com.metix.testproject.service.dto.RestaurantCategoryDTO;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantCategoryResource {

    private final RestaurantCategoryService restaurantCategoryService;

    public RestaurantCategoryResource(RestaurantCategoryService restaurantCategoryService) {
        this.restaurantCategoryService = restaurantCategoryService;
    }


    @GetMapping("/restaurantCategories")
    public List<RestaurantCategoryDTO> selectRestaurant() {
        return restaurantCategoryService.getRestaurantCategory();
    }

    @PostMapping("/restaurantCategorycreate")
    public RestaurantCategoryDTO createRestaurantCategory(@RequestBody RestaurantCategoryDTO restaurantCategoryDTO) throws URISyntaxException {

        RestaurantCategoryDTO restaurantCategoryDTO1 = restaurantCategoryService.createRestaurant(restaurantCategoryDTO);
        return restaurantCategoryDTO1;
    }

    @DeleteMapping("/restaurantCategorydelete/{id}")
    public void deleteRestaurantCategory(@PathVariable Long id) {

        restaurantCategoryService.deleteRestaurantCategory(id);

    }

    @PutMapping("/restaurantCategoryupdate")
    public RestaurantCategoryDTO updateRestaurantCategory (@RequestBody RestaurantCategoryDTO restaurantCategoryDTO) throws URISyntaxException {

        RestaurantCategoryDTO restaurantCategoryDTO1 = restaurantCategoryService.updateRestaurantCategory(restaurantCategoryDTO);
        return restaurantCategoryDTO1;
    }
}
