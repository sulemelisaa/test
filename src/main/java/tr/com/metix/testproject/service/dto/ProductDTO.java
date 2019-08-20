package tr.com.metix.testproject.service.dto;

public class ProductDTO {
    private Long id;
    private  String name;
    private  int price;
    private  Long restaurantCategoryId;


    private int stockTotalOutput;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getRestaurantCategoryId() {
        return restaurantCategoryId;
    }

    public void setRestaurantCategoryId(Long restaurantCategoryId) {
        this.restaurantCategoryId = restaurantCategoryId;
    }



    public int getStockTotalOutput() {
        return stockTotalOutput;
    }

    public void setStockTotalOutput(int stockTotalOutput) {
        this.stockTotalOutput = stockTotalOutput;
    }


}
