package com.example.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {


    @EmbeddedId
    private ItemID id = new ItemID();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orderId;


    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;

    @Column(name = "count")
    private int count;

    public Item() {
    }

    public Item(ItemID id, Orders orderId, Product productId, int count) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
    }

    public ItemID getId() {
        return id;
    }

    public void setId(ItemID id) {
        this.id = id;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return count == item.count &&
                Objects.equals(id, item.id) &&
                Objects.equals(orderId, item.orderId) &&
                Objects.equals(productId, item.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, count);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }
}
