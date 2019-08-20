package tr.com.metix.testproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.*;
import tr.com.metix.testproject.repository.ProductRepository;
import tr.com.metix.testproject.repository.RestaurantCategoryRepository;
import tr.com.metix.testproject.repository.RestaurantRepository;
import tr.com.metix.testproject.repository.StockRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.ProductDTO;
import tr.com.metix.testproject.service.mapper.ProductMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantCategoryRepository restaurantCategoryRepository;
    private final StockRepository stockRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, UserService userService, RestaurantRepository restaurantRepository, RestaurantCategoryRepository restaurantCategoryRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userService = userService;
        this.restaurantRepository = restaurantRepository;
        this.restaurantCategoryRepository = restaurantCategoryRepository;
        this.stockRepository = stockRepository;
    }


    public List<ProductDTO> getProduct(){
        List<ProductDTO>  productDTOS = productRepository.findAll().stream().map(productMapper::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return productDTOS;
    }

    public ProductDTO createProduct(ProductDTO productDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // curretUser tuır
        List<Restaurant> restaurants = restaurantRepository.findAllByUser_Id(u.get().getId()); // currentUser'ın sahıp o tum resaurantların lıstesı


        List<Long> restaurantsId = new ArrayList<>();
        List<Long> restaurantCategoriesId = new ArrayList<>();


        if(restaurants.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir restaurant yok ", null, "idexists");
        }

        for(int i=0; i<restaurants.size(); i++){
            restaurantsId.add(restaurants.get(i).getId());
        }

        List<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findAllByRestaurant_IdIn(restaurantsId); // currentUser'ın sahıp old. tum restaurantların lıstesı

        if(restaurantCategory.isEmpty()) {
            throw new BadRequestAlertException("Sahip olduğunuz bir restaurantCategory yok ", null, "idexists");
        }

        for(int i=0; i<restaurantCategory.size(); i++){
            restaurantCategoriesId.add(restaurantCategory.get(i).getId());
        }

        if(!restaurantCategoriesId.contains(productDTO.getRestaurantCategoryId())){
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'a ürün ekleyebilirsiniz", null, "idexists");
        }

        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);

        Stock stock = new Stock();
        stock.setStockInput(0);
        stock.setProducts(product);
        stock = stockRepository.save(stock);

        return productMapper.toDTO(product);

    }


    public void deleteProduct(Long id) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser umsatır
        Optional<Product> product = productRepository.findById(id); // currentUser uyusan ıdnın tum satırı

        if(!product.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip ürün bulunamadı", null, "test");
        }

        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findById(product.get().getRestaurantCategory().getId());
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
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'ın ürününü silebılırsınız", null, "idexists");
        }

        /////// stock tablosunda product_id oldugu ıcın once stock tablosundan sılınıp sonra producttan sılmeye ızın verıyor
        Optional<Stock> stock = stockRepository.findAllByProducts_Id(product.get().getId());
        stockRepository.deleteById(stock.get().getId());
        //////////////
        productRepository.deleteById(id);

    }


    public ProductDTO updateProduct(ProductDTO productDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tuır
        List<Restaurant> restaurants = restaurantRepository.findAllByUser_Id(u.get().getId()); // currentUser'ın sahıp old. tum restaurantların lıstesı
        Optional<Product> product = productRepository.findById(productDTO.getId());
        Optional<Stock> stock = stockRepository.findAllByProducts_Id(product.get().getId());


        if(!product.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip urun bulunmamaktadır.", null, "idexists");
        }

        List<Long> restaurantsId = new ArrayList<>();
        List<Long> restaurantCategoriesId = new ArrayList<>();
//
//        System.out.println("REstaurant current  : " + restaurants.get(0).getId());
//        System.out.println("REstaurant current  : " + restaurants.get(1).getId());

        if(restaurants.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir restaurant yok ", null, "idexists");
        }

        for(int i=0; i<restaurants.size(); i++){
            restaurantsId.add(restaurants.get(i).getId());
        }

        List<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findAllByRestaurant_IdIn(restaurantsId); // currentUser'ın sahıp old. tum restaurantların lıstesı

        if(restaurantCategory.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir restaurantCategory yok ", null, "idexists");
        }

        for(int i=0; i<restaurantCategory.size(); i++){
            restaurantCategoriesId.add(restaurantCategory.get(i).getId());
        }

        if(!restaurantCategoriesId.contains(productDTO.getRestaurantCategoryId())){
//            System.out.println("İCEREN : " + restaurantCategoryDTO.getRestaurantId());
            throw new BadRequestAlertException("Yalnızca sahibi olduğunuz Restaurant'ın ürününü guncelleyebılırsınız", null, "idexists");
        }

        Product product1 = productMapper.toEntity(productDTO);



        product1 = productRepository.save(product1);
        return productMapper.toDTO(product1);
    }

}
