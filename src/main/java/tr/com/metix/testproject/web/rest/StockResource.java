package tr.com.metix.testproject.web.rest;
import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.domain.Product;
import tr.com.metix.testproject.repository.ProductRepository;
import tr.com.metix.testproject.service.StockService;
import tr.com.metix.testproject.service.dto.StockDTO;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StockResource {

    private final StockService stockService;
    private final ProductRepository productRepository;

    public StockResource(StockService stockService, ProductRepository productRepository) {
        this.stockService = stockService;
        this.productRepository = productRepository;
    }


    @GetMapping("/stocks")
    public List<StockDTO> selectStock() {
        return stockService.getStock();
    }

    @PostMapping("/createStock")
    public StockDTO createStock(@RequestBody StockDTO stockDTO) throws URISyntaxException {

        StockDTO stockDTO1 = stockService.createStock(stockDTO);

        Optional<Product> product = productRepository.findById(stockDTO1.getProductId());

        System.out.println("\ntoplam girdi : " + product.get().getStockTotalInput());
        System.out.println("\nStock fark : " + product.get().getRemainingStok());

        return stockDTO1;
    }

    @DeleteMapping("/deleteStock/{id}")
    public void deleteStock(@PathVariable Long id) {

        stockService.deleteStock(id);

    }

    @PutMapping("/updateStock")
    public StockDTO updateStock (@RequestBody StockDTO stockDTO) throws URISyntaxException {

        StockDTO stockDTO1 = stockService.updateStock(stockDTO);
        Optional<Product> product = productRepository.findById(stockDTO1.getProductId());

        System.out.println("\ntoplam girdi : " + product.get().getStockTotalInput());
        System.out.println("\nStock fark : " + product.get().getRemainingStok());

        return stockDTO1;
    }
}
