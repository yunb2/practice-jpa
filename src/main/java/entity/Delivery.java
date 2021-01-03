package entity;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS")
    private DeliveryStatus status;

    public Delivery() { }

    public Delivery(Member member) {
        this.city = member.getCity();
        this.street = member.getStreet();
        this.zipcode = member.getZipcode();
        this.status = DeliveryStatus.READY;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
