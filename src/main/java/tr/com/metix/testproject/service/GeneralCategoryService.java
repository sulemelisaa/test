package tr.com.metix.testproject.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.GeneralCategory;
import tr.com.metix.testproject.domain.Restaurant;
import tr.com.metix.testproject.domain.RestaurantCategory;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.GeneralCategoryRepository;
import tr.com.metix.testproject.repository.RestaurantRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.GeneralCategoryDTO;
import tr.com.metix.testproject.service.dto.RestaurantCategoryDTO;
import tr.com.metix.testproject.service.mapper.GeneralCategoryMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeneralCategoryService {

    private final GeneralCategoryRepository generalCategoryRepository;
    private final GeneralCategoryMapper generalCategoryMapper;
    private final UserService userService;
    private final RestaurantRepository restaurantRepository;

    public GeneralCategoryService(GeneralCategoryRepository generalCategoryRepository, GeneralCategoryMapper generalCategoryMapper, UserService userService, RestaurantRepository restaurantRepository) {
        this.generalCategoryRepository = generalCategoryRepository;
        this.generalCategoryMapper = generalCategoryMapper;
        this.userService = userService;
        this.restaurantRepository = restaurantRepository;
    }

    public List<GeneralCategoryDTO> getGeneralCategory(){
        List<GeneralCategoryDTO>  generalCategory = generalCategoryRepository.findAll().stream().map(generalCategoryMapper::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return generalCategory;
    }

    public GeneralCategoryDTO createGC (GeneralCategoryDTO generalCategoryDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tm satı
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

        System.out.println("IDELR : " + restaurantsId);

        if ( !restaurantsId.contains(generalCategoryDTO.getRestaurants().stream().findFirst().get().getId()) ) {
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'a genel kategori ekleyebilirsiniz", null, "idexists");
        }

        GeneralCategory generalCategory = generalCategoryMapper.toEntity(generalCategoryDTO);

        generalCategory = generalCategoryRepository.save(generalCategory);
        return generalCategoryMapper.toDTO(generalCategory);

    }

    public void deleteGeneralCategory(Long id) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser um satır
        Optional<GeneralCategory> generalCategory = generalCategoryRepository.findById(id); // currentUser uyusan ıdnın tum satırı

        if(!generalCategory.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip genel kategori bulunamadı", null, "test");
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

        if(!restaurantsId.contains(generalCategory.get().getRestaurants().stream().findFirst().get().getId())){
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'ın genel kategorisini silebılırsınız", null, "idexists");
        }

        generalCategoryRepository.deleteById(id);

    }


    public GeneralCategoryDTO updateGeneralCategory(GeneralCategoryDTO generalCategoryDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser um satır
        Optional<GeneralCategory> generalCategory = generalCategoryRepository.findById(generalCategoryDTO.getId()); // currentUser uyusan ıdnın tum satırı

        if (!generalCategory.isPresent()) {
            throw new BadRequestAlertException("Bu id'ye sahip genel kategori bulunamadı! ", null, "idnull");
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

        if(!restaurantsId.contains(generalCategory.get().getRestaurants().stream().findFirst().get().getId())){
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'ın genel kategorisini güncelleyebılırsınız", null, "idexists");
        }

        if(!restaurantsId.contains(generalCategoryDTO.getRestaurants().stream().findFirst().get().getId())){
            throw new BadRequestAlertException("Güncellemek istediğiniz restaurantId sıze aıt degıl", null, "idexists");
        }


        GeneralCategory generalCategory1 = generalCategoryMapper.toEntity(generalCategoryDTO);

        generalCategory1 = generalCategoryRepository.save(generalCategory1);
        return generalCategoryMapper.toDTO(generalCategory1);
    }




}
