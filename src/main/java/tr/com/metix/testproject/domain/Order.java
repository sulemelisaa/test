package tr.com.metix.testproject.domain;
import org.hibernate.annotations.Formula;
import org.springframework.format.datetime.joda.DateTimeParser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "order_date")
    private Date orderDate;


    @ManyToOne
    private  User user;

    @Column(name = "totalPrice")
    @Formula("(select sum(po.total_product_price)from product_order po inner join productorder_order p on po.id = p.productorder_id inner join productorder_product p2 on po.id = p2.productorder_id where p.order_id=id)")
    private  int totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
