package tr.com.metix.testproject.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.Order;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.OrderRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.OrderDTO;
import tr.com.metix.testproject.service.mapper.OrderMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
    }


    public List<OrderDTO> getOrder(){
        List<OrderDTO>  order = orderRepository.findAll().stream().map(orderMapper::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return order;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) throws BadRequestAlertException {

        Optional<Order> order = orderRepository.findById(orderDTO.getId());

        if (order.isPresent()) {
            throw new BadRequestAlertException("Bu id'ye sahip sipariş zaten kayıt edilmiş !! ", null, "idexists");
        }

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır

        if(u.get().getId()!=orderDTO.getUserId()){
            throw new BadRequestAlertException("Sadece kendı ıd'niz uzerınden ekleme yapabılırsınız ", null, "idexists");
        }

        Order order1 = orderMapper.toEntity(orderDTO);

        order1.setOrderDate(orderDTO.getOrderDate());
        order1.setUser(u.get());


        order1 = orderRepository.save(order1);
        return orderMapper.toDTO(order1);
    }

    public void deleteOrder(Long id) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır
        Optional<Order> order = orderRepository.findById(id); // currentUser uyusan ıdnın tum satırı

        if(!order.isPresent()){
            throw new BadRequestAlertException("Bu id'ye sahip sipariş bulunamadı", null, "test");
        }

        if (u.get().getId() != order.get().getUser().getId()) {
            throw new BadRequestAlertException("Yalnızca Sipariş Sahibi Sipariş Silebilir!! ", null, "test");
        }

        /////// restcategory tablosunda rest_id oldugu ıcın once restcategory tablosundan sılınıp sonra restauranttan sılmeye ızın verıyor
//        Optional<RestaurantCategory> restaurantCategory = restaurantCategoryRepository.findAllByRestaurant_Id(restaurant.get().getId());
//        restaurantCategoryRepository.deleteById(restaurantCategory.get().getId());
        //////////////

        orderRepository.deleteById(id);

    }

    public OrderDTO updateOrder (OrderDTO orderDTO) throws BadRequestAlertException {

        Optional<Order> order = orderRepository.findById(orderDTO.getId());
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get()); // currentUser tum satır

        if (!order.isPresent()) {
            throw new BadRequestAlertException("Bu id'ye sahip sipariş bulunamadı! ", null, "idnull");
        }

        if (u.get().getId() != order.get().getUser().getId()) {
            throw new BadRequestAlertException("Yalnızca Sipariş Sahibi Sipariş güncelleyebilir!! ", null, "test");
        }

        if(orderDTO.getUserId()!= u.get().getId()){
            throw new BadRequestAlertException("Güncellemek istediğiniz userID sıze aıt degıl", null, "idexists");
        }


        Order order1 = orderMapper.toEntity(orderDTO);

        order1.setOrderDate(orderDTO.getOrderDate());
        order1.setUser(u.get());


        order1 = orderRepository.save(order1);
        return orderMapper.toDTO(order1);
    }


}
