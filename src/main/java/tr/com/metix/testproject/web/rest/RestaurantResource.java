package tr.com.metix.testproject.web.rest;

import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.service.RestaurantService;
import tr.com.metix.testproject.service.dto.RestaurantDTO;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantResource {

    private final RestaurantService restaurantService;

    public RestaurantResource(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/restaurants")
    public List<RestaurantDTO> selectRestaurant() {
        return restaurantService.getRestaurant();
    }


    @PostMapping("/restaurantcreate")
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {


        RestaurantDTO restaurantDTO1 = restaurantService.createRestaurant(restaurantDTO);
        return restaurantDTO1;
    }

    @DeleteMapping("/restaurantdelete/{id}")
    public void deleteRestaurant(@PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

    }

    @PutMapping("/restaurantupdate")
    public RestaurantDTO updateRestaurant (@RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {

        RestaurantDTO restaurantDTO1 = restaurantService.updateRestaurant(restaurantDTO);
        return restaurantDTO1;
    }


}
