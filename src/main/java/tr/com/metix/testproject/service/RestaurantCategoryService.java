package tr.com.metix.testproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.Restaurant;
import tr.com.metix.testproject.domain.RestaurantCategory;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.RestaurantCategoryRepository;
import tr.com.metix.testproject.repository.RestaurantRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.RestaurantCategoryDTO;
import tr.com.metix.testproject.service.mapper.RestaurantCategoryMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantCategoryService {

    private final RestaurantCategoryRepository restaurantCategoryRepository;
    private final RestaurantCategoryMapper restaurantCategoryMapper;
    private final UserService userService;
    private final RestaurantRepository restaurantRepository;

    public RestaurantCategoryService(RestaurantCategoryRepository restaurantCategoryRepository, RestaurantCategoryMapper restaurantCategoryMapper, UserService userService, RestaurantRepository restaurantRepository) {
        this.restaurantCategoryRepository = restaurantCategoryRepository;
        this.restaurantCategoryMapper = restaurantCategoryMapper;
        this.userService = userService;
        this.restaurantRepository = restaurantRepository;
    }


    public List<RestaurantCategoryDTO> getRestaurantCategory(){
        List<RestaurantCategoryDTO>  restaurant = restaurantCategoryRepository.findAll().stream().map(restaurantCategoryMapper::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return restaurant;
    }


    public RestaurantCategoryDTO createRestaurant (RestaurantCategoryDTO restaurantCategoryDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır
        List<Restaurant> restaurants = restaurantRepository.findAllByUser_Id(u.get().getId()); // currentUser'ın sahıp old. tum restaurantların lıstesı

        List<Long> restaurantsId = new ArrayList<>();
//
//        System.out.println("REstaurant current  : " + restaurants.get(0).getId());
//        System.out.println("REstaurant current  : " + restaurants.get(1).getId());

        if(restaurants.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir restaurant yok ", null, "idexists");
        }

        for(int i=0; i<restaurants.size(); i++){
            restaurantsId.add(restaurants.get(i).getId());
        }

//        System.out.println("IDELR : " + restaurantsId);

        if(!restaurantsId.contains(restaurantCategoryDTO.getRestaurantId())){
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'a kategori ekleyebilirsiniz", null, "idexists");
        }


        RestaurantCategory restaurantCategory = restaurantCategoryMapper.toEntity(restaurantCategoryDTO);

        restaurantCategory = restaurantCategoryRepository.save(restaurantCategory);
        return restaurantCategoryMapper.toDTO(restaurantCategory);

    }


    public void deleteRestaurantCategory(Long id) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser um satır
        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findById(id); // currentUser uyusan ıdnın tum satırı

        if(!restaurantCategory.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip restaurant kategori bulunamadı", null, "test");
        }

        List<Restaurant> restaurants = restaurantRepository.findAllByUser_Id(u.get().getId()); // currentUser'ın sahıp old. tum restaurantların lıstesı

        List<Long> restaurantsId = new ArrayList<>();
//
//        System.out.println("REstaurant current  : " + restaurants.get(0).getId());
//        System.out.println("REstaurant current  : " + restaurants.get(1).getId());

        if(restaurants.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir restaurant yok ", null, "idexists");
        }

        for(int i=0; i<restaurants.size(); i++){
            restaurantsId.add(restaurants.get(i).getId());
        }

//        System.out.println("IDELR : " + restaurantsId);

        if(!restaurantsId.contains(restaurantCategory.get().getRestaurant().getId())){
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'ın kategorisini silebılırsınız", null, "idexists");
        }

        restaurantCategoryRepository.deleteById(id);

    }

    public RestaurantCategoryDTO updateRestaurantCategory(RestaurantCategoryDTO restaurantCategoryDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser um satır
        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findById(restaurantCategoryDTO.getId()); // currentUser uyusan ıdnın tum satırı

        if (!restaurantCategory.isPresent()) {
            throw new BadRequestAlertException("Bu id'ye sahip restaurant kategorisi bulunamadı! ", null, "idnull");
        }

        List<Restaurant> restaurants = restaurantRepository.findAllByUser_Id(u.get().getId()); // currentUser'ın sahıp old. tum restaurantların lıstesı

        List<Long> restaurantsId = new ArrayList<>();
//
//        System.out.println("REstaurant current  : " + restaurants.get(0).getId());
//        System.out.println("REstaurant current  : " + restaurants.get(1).getId());

        if(restaurants.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir restaurant yok ", null, "idexists");
        }

        for(int i=0; i<restaurants.size(); i++){
            restaurantsId.add(restaurants.get(i).getId());
        }

//        System.out.println("IDELR : " + restaurantsId);

        if(!restaurantsId.contains(restaurantCategory.get().getRestaurant().getId())){
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'ın kategorisini güncelleyebılırsınız", null, "idexists");
        }

        if(!restaurantsId.contains(restaurantCategoryDTO.getRestaurantId())){
            throw new BadRequestAlertException("Güncellemek istediğiniz restaurantId sıze aıt degıl", null, "idexists");
        }


        RestaurantCategory restaurantCategory1 = restaurantCategoryMapper.toEntity(restaurantCategoryDTO);

        restaurantCategory1 = restaurantCategoryRepository.save(restaurantCategory1);
        return restaurantCategoryMapper.toDTO(restaurantCategory1);
    }




}
