package tr.com.metix.testproject.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.*;
import tr.com.metix.testproject.repository.OrderRepository;
import tr.com.metix.testproject.repository.ProductOrderRepository;
import tr.com.metix.testproject.repository.ProductRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.ProductOrderDTO;
import tr.com.metix.testproject.service.dto.RestaurantCategoryDTO;
import tr.com.metix.testproject.service.mapper.ProductOrderMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderMapper productOrderMapper;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ProductOrderService(ProductOrderRepository productOrderRepository, ProductOrderMapper productOrderMapper, UserService userService, OrderRepository orderRepository, ProductRepository productRepository) {
        this.productOrderRepository = productOrderRepository;
        this.productOrderMapper = productOrderMapper;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<ProductOrderDTO> getProductOrder(){
        List<ProductOrderDTO>  productOrderDTOS = productOrderRepository.findAll().stream().map(productOrderMapper::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return productOrderDTOS;
    }


    public ProductOrderDTO createProductOrder (ProductOrderDTO productOrderDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır
        List<Order> orders = orderRepository.findAllByUser_Id(u.get().getId());
        Optional<Product> products = productRepository.findById(productOrderDTO.getProducts().stream().findFirst().get().getId());

        List<Long> orderIds = new ArrayList<>(); // currentUSer'ın sahıp oldugu sıparıslerın ıd'sı

        if(orders.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir sipariş yok ", null, "idexists");
        }

        for(int i=0; i<orders.size(); i++){
            orderIds.add(orders.get(i).getId());
        }

        //   System.out.println("USERIN SIPARISLERI : " + orderIds.get(0));

        if(!orderIds.contains(productOrderDTO.getOrders().stream().findFirst().get().getId())){
            throw new BadRequestAlertException("Sipariş detayı ıle sıparıs uyusmuyor", null, "idexists");
        }

        if(!products.isPresent()){
            throw new BadRequestAlertException("Bu ıd'ye sahıp urun bulunmamaktadır.", null, "idexists");
        }

        ProductOrder productOrder = productOrderMapper.toEntity(productOrderDTO);
        productOrder = productOrderRepository.save(productOrder);
        return productOrderMapper.toDTO(productOrder);

    }

    public void deleteProductOrder(Long id) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser um satır
        Optional<ProductOrder> productOrder = productOrderRepository.findById(id); // currentUser uyusan ıdnın tum satırı

        List<Order> orders = orderRepository.findAllByUser_Id(u.get().getId());


        if(!productOrder.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip siparis detayı bulunamadı", null, "test");
        }

        List<Long> orderIds = new ArrayList<>(); // currentUSer'ın sahıp oldugu sıparıslerın ıd'sı

        if(orders.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir sipariş yok ", null, "idexists");
        }

        for(int i=0; i<orders.size(); i++){
            orderIds.add(orders.get(i).getId());
        }


        if(!orderIds.contains(productOrder.get().getOrders().stream().findFirst().get().getId())){
            throw new BadRequestAlertException("Sipariş detayı ıle sıparıs uyusmuyor", null, "idexists");
        }

        productOrderRepository.deleteById(id);

    }


    public ProductOrderDTO updtaeProductOrder(ProductOrderDTO productOrderDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser um satır
        Optional<ProductOrder> productOrder = productOrderRepository.findById(productOrderDTO.getId()); // currentUser uyusan ıdnın tum satırı
        Optional<Product> products = productRepository.findById(productOrderDTO.getProducts().stream().findFirst().get().getId());

        List<Order> orders = orderRepository.findAllByUser_Id(u.get().getId());
        List<Long> orderIds = new ArrayList<>(); // currentUSer'ın sahıp oldugu sıparıslerın ıd'sı


        if(!productOrder.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip siparis detayı bulunamadı", null, "test");
        }

        if(orders.isEmpty()){
            throw new BadRequestAlertException("Sahip olduğunuz bir sipariş yok ", null, "idexists");
        }

        for(int i=0; i<orders.size(); i++){
            orderIds.add(orders.get(i).getId());
        }

        //   System.out.println("USERIN SIPARISLERI : " + orderIds.get(0));

        if(!orderIds.contains(productOrderDTO.getOrders().stream().findFirst().get().getId())){
            throw new BadRequestAlertException("Sipariş detayı ıle sıparıs uyusmuyor", null, "idexists");
        }

        if(!products.isPresent()){
            throw new BadRequestAlertException("Bu ıd'ye sahıp urun bulunmamaktadır.", null, "idexists");
        }

        ProductOrder productOrder1 = productOrderMapper.toEntity(productOrderDTO);
        productOrder1 = productOrderRepository.save(productOrder1);
        return productOrderMapper.toDTO(productOrder1);


    }


}
