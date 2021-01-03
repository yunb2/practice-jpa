package entity.item;

import entity.BaseEntity;
import entity.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "ITEM")
public abstract class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "STOCKQUANTITY")
    private Integer stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories;

    public Item() { }

    public Item(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = new ArrayList<>();
    }

    public Integer getPrice() {
        return price;
    }

    public void sell(int count) {
        this.stockQuantity -= count;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }
}
