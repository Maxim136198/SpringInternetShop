package by.itstep.springInternetShop.dao.entity;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User customer;

    @Column(name = "date_of_purchase", nullable = false)
    private LocalDateTime dateOfPurchase;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @OneToMany(mappedBy = "order")
    private Set<Item> items;

    public Order() {
    }

    public Order(Long id, User customer, LocalDateTime dateOfPurchase, Double purchasePrice, Set<Item> items) {
        this.id = id;
        this.customer = customer;
        this.dateOfPurchase = dateOfPurchase;
        this.purchasePrice = purchasePrice;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", dateOfPurchase=" + dateOfPurchase +
                ", purchasePrice=" + purchasePrice +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(dateOfPurchase, order.dateOfPurchase) &&
                Objects.equals(purchasePrice, order.purchasePrice) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, dateOfPurchase, purchasePrice, items);
    }
}


