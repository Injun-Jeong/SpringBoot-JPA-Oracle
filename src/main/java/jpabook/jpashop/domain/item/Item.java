package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_price")
    private int price;

    @Column(name = "item_stock_quantity")
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /* business logic */

    /**
     * supply some quantity of stock
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * remove some quantity of stock
     */
    public void removeStock(int quantity) {
        int restStrock = this.stockQuantity - quantity;
        if (restStrock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStrock;
    }

}