package tr.com.metix.testproject.domain;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Stock implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @ManyToOne
    private Product products ;

    @Column(name = "stock_input")
    private int stockInput;

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }
//    @ManyToMany
//    @JoinTable(name = "stock_product",
//        joinColumns = @JoinColumn(name = "stock_id"),
//        inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products = new HashSet<>();




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStockInput() {
        return stockInput;
    }

    public void setStockInput(int stockInput) {
        this.stockInput = stockInput;
    }




}
