package entity;

import entity.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column(name = "ORDERPRICE")
    private Integer price;

    @Column(name = "COUNT")
    private Integer count;

    public OrderItem() { }

    public OrderItem(Item item, Integer count) {
        this.item = item;
        this.count = count;
        this.price = item.getPrice() * count;
        item.sell(count);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", item=" + item +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
