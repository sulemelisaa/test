package tr.com.metix.testproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.*;
import tr.com.metix.testproject.repository.ProductRepository;
import tr.com.metix.testproject.repository.RestaurantCategoryRepository;
import tr.com.metix.testproject.repository.RestaurantRepository;
import tr.com.metix.testproject.repository.StockRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.StockDTO;
import tr.com.metix.testproject.service.mapper.StockMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final RestaurantCategoryRepository restaurantCategoryRepository;
    private final RestaurantRepository restaurantRepository;

    public StockService(StockRepository stockRepository, StockMapper stockMapper, UserService userService, ProductRepository productRepository, RestaurantCategoryRepository restaurantCategoryRepository, RestaurantRepository restaurantRepository) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
        this.userService = userService;
        this.productRepository = productRepository;
        this.restaurantCategoryRepository = restaurantCategoryRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public List<StockDTO> getStock(){
        List<StockDTO>  stock = stockRepository.findAll().stream().map(stockMapper::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return stock;
    }

    public StockDTO createStock (StockDTO stockDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır

        Optional<Product> product = productRepository.findById(stockDTO.getProductId());


        if(!product.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahıp urun bulunmamaktadır", null, "idexists");
        }

        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findById(product.get().getRestaurantCategory().getId());
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantCategory.get().getRestaurant().getId());



        if(u.get().getId()!=restaurant.get().getUser().getId()){
            throw new BadRequestAlertException("Yalnızca Sahıbı oldugunuz restauranta urun stoğu ekleyebılırsınız", null, "idexists");
        }


        Stock stock = stockMapper.toEntity(stockDTO);


        stock = stockRepository.save(stock);



        return stockMapper.toDTO(stock);

    }

    public void deleteStock(Long id) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser um satır

        Optional<Stock> stock = stockRepository.findById(id); // currentUser uyusan ıdnın tum satırı

        if(!stock.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip stock ıslemı bulunamadı", null, "test");
        }

        Optional<Product> product = productRepository.findById(stock.get().getProducts().getId());
        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findById(product.get().getRestaurantCategory().getId());
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantCategory.get().getRestaurant().getId());

        if(u.get().getId()!=restaurant.get().getUser().getId()){
            throw new BadRequestAlertException("Yalnızca Sahıbı oldugunuz restauranın urun stoğunu silebilirsiniz", null, "idexists");
        }


        stockRepository.deleteById(id);

    }

    public StockDTO updateStock(StockDTO stockDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır

        Optional<Stock> stock1 = stockRepository.findById(stockDTO.getId());

        if(!stock1.isPresent()){
            throw new BadRequestAlertException("Bu ıd'ye sahıp bır stock ıslemı bulunmamaktadır", null, "idexists");
        }

        Optional<Product> product = productRepository.findById(stock1.get().getProducts().getId());

        if(!product.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahıp urun bulunmamaktadır", null, "idexists");
        }



        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findById(product.get().getRestaurantCategory().getId());
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantCategory.get().getRestaurant().getId());

        if(u.get().getId()!=restaurant.get().getUser().getId()){
            throw new BadRequestAlertException("Yalnızca Sahıbı oldugunuz restaurantın urun stoğunu guncelleyebılırsınız", null, "idexists");
        }


        Stock stock = stockMapper.toEntity(stockDTO);

        stock = stockRepository.save(stock);
        product.get().setStockTotalInput(stock.getStockInput());
        return stockMapper.toDTO(stock);
    }

}
