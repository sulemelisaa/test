package tr.com.metix.testproject.web.rest;

import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.domain.Order;
import tr.com.metix.testproject.repository.OrderRepository;
import tr.com.metix.testproject.service.ProductOrderService;
import tr.com.metix.testproject.service.dto.ProductOrderDTO;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductOrderResource {
    private final ProductOrderService productOrderService;
    private final OrderRepository orderRepository;

    public ProductOrderResource(ProductOrderService productOrderService, OrderRepository orderRepository) {
        this.productOrderService = productOrderService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/productOrders")
    public List<ProductOrderDTO> selectProductOrder() {
        return productOrderService.getProductOrder();
    }

    @PostMapping("/productOrdercreate")
    public ProductOrderDTO createProductOrder(@RequestBody ProductOrderDTO productOrderDTO) throws URISyntaxException {

        ProductOrderDTO restaurantCategoryDTO1 = productOrderService.createProductOrder(productOrderDTO);

        Optional<Order> order = orderRepository.findById(restaurantCategoryDTO1.getOrders().stream().findFirst().get().getId());

        System.out.println("\ntoplam girdi : " + order.get().getTotalPrice());
        return restaurantCategoryDTO1;
    }


    @DeleteMapping("/deleteProductOrder/{id}")
    public void deleteProductOrder(@PathVariable Long id) {

        productOrderService.deleteProductOrder(id);

    }

    @PutMapping("/updateProductOrder")
    public ProductOrderDTO updateProductOrder (@RequestBody ProductOrderDTO productOrderDTO) throws URISyntaxException {

        ProductOrderDTO productOrderDTO1 = productOrderService.updtaeProductOrder(productOrderDTO);
        return productOrderDTO1;
    }
}
