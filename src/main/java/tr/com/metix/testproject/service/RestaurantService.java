package tr.com.metix.testproject.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.GeneralCategory;
import tr.com.metix.testproject.domain.Restaurant;
import tr.com.metix.testproject.domain.RestaurantCategory;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.GeneralCategoryRepository;
import tr.com.metix.testproject.repository.RestaurantCategoryRepository;
import tr.com.metix.testproject.repository.RestaurantRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.RestaurantDTO;
import tr.com.metix.testproject.service.mapper.RestaurantMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final UserService userService;
    private final RestaurantCategoryRepository restaurantCategoryRepository;
    private final GeneralCategoryRepository generalCategoryRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper, UserService userService, RestaurantCategoryRepository restaurantCategoryRepository, GeneralCategoryRepository generalCategoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.userService = userService;
        this.restaurantCategoryRepository = restaurantCategoryRepository;
        this.generalCategoryRepository = generalCategoryRepository;
    }


    public List<RestaurantDTO> getRestaurant(){
        List<RestaurantDTO>  restaurant = restaurantRepository.findAll().stream().map(restaurantMapper::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return restaurant;
    }


    public RestaurantDTO createRestaurant (RestaurantDTO restaurantDTO) throws BadRequestAlertException {

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantDTO.getId());

        if (restaurant.isPresent()) {
            throw new BadRequestAlertException("Bu id'ye sahip restaurant zaten kayıt edilmiş !! ", null, "idexists");
        }




        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır

        if(u.get().getId()!=restaurantDTO.getUser()){
            throw new BadRequestAlertException("Sadece kendı ıd'niz uzerınden ekleme yapabılırsınız ", null, "idexists");
        }

        Restaurant restaurant1 = restaurantMapper.toEntity(restaurantDTO);

        restaurant1.setName(restaurantDTO.getName());
        restaurant1.setUser(u.get());


        restaurant1 = restaurantRepository.save(restaurant1);
        return restaurantMapper.toDTO(restaurant1);
    }


    public void deleteRestaurant(Long id) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır
        Optional<Restaurant> restaurant = restaurantRepository.findById(id); // currentUser uyusan ıdnın tum satırı

        if(!restaurant.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip restaurant bulunamadı", null, "test");
        }

        if (u.get().getId() != restaurant.get().getUser().getId()) {
            throw new BadRequestAlertException("Yalnızca Restaurant Sahibi Restaurant Silebilir!! ", null, "test");
        }



        /////// restcategory tablosunda rest_id oldugu ıcın once restcategory tablosundan sılınıp sonra restauranttan sılmeye ızın verıyor
        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findAllByRestaurant_Id(restaurant.get().getId());
        List<GeneralCategory> generalCategory = generalCategoryRepository.findAllByRestaurants_Id(restaurant.get().getId());


        if(restaurantCategory.isPresent()) {
            restaurantCategoryRepository.deleteById(restaurantCategory.get().getId());
            // restaurantRepository.deleteById(id);
            //////////////
        }

        if(!generalCategory.isEmpty()){
            for(int i=0;i<generalCategory.size();i++) {
                generalCategoryRepository.deleteById(generalCategory.get(i).getId());
            }
        }

        restaurantRepository.deleteById(id);

    }

    public RestaurantDTO updateRestaurant (RestaurantDTO restaurantDTO) throws BadRequestAlertException {

        Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurantDTO.getId());
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır

        if (!restaurant1.isPresent()) {
            throw new BadRequestAlertException("Bu id'ye sahip restaurant bulunamadı! ", null, "idnull");
        }

        if (u.get().getId() != restaurant1.get().getUser().getId()) {
            throw new BadRequestAlertException("Yalnızca Restaurant Sahibi Restaurant güncelleyebilir!! ", null, "test");
        }

        if(restaurantDTO.getUser()!= u.get().getId()){
            throw new BadRequestAlertException("Güncellemek istediğiniz userID sıze aıt degıl", null, "idexists");
        }


        Restaurant restaurant2 = restaurantMapper.toEntity(restaurantDTO);

        restaurant2.setName(restaurantDTO.getName());
        restaurant2.setUser(u.get());

        restaurant2 = restaurantRepository.save(restaurant2);
        return restaurantMapper.toDTO(restaurant2);
    }


}
