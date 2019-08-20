package tr.com.metix.testproject.domain;
import org.hibernate.annotations.Formula;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "price")
    private  int price;


    @ManyToOne
    private  RestaurantCategory restaurantCategory;

    @Column(name = "stock_total_output")
    private  int stockTotalOutput;

    //
    @Column(name = "stock_total_input")
    @Formula("(select sum(ss.stock_input) from stock ss where ss.products_id = id)")
    private  int stockTotalInput;


    @Transient
    @Column(name = "remaining_stok")
    @Formula("stockTotalInput-stockTotalOutput")
    private  int remainingStok;


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

    public RestaurantCategory getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(RestaurantCategory restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getStockTotalInput() {
        return stockTotalInput;
    }

    public void setStockTotalInput(int stockTotalInput) {
        this.stockTotalInput = stockTotalInput;
    }

    public int getStockTotalOutput() {
        return stockTotalOutput;
    }

    public void setStockTotalOutput(int stockTotalOutput) {
        this.stockTotalOutput = stockTotalOutput;
    }

    public int getRemainingStok() {
        return stockTotalInput-stockTotalOutput;
    }

}
