
package tr.com.metix.testproject.service.dto;

public class StockDTO {
    private Long id;
    private Long productId ;
    private int stockInput;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getStockInput() {
        return stockInput;
    }

    public void setStockInput(int stockInput) {
        this.stockInput = stockInput;
    }
}
