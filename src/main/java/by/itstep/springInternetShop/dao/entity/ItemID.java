package by.itstep.springInternetShop.dao.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemID implements Serializable {

    private Long orderId;

    private Long productId;

    public ItemID() {
    }

    public ItemID(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemID itemID = (ItemID) o;
        return Objects.equals(orderId, itemID.orderId) &&
                Objects.equals(productId, itemID.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

    @Override
    public String toString() {
        return "ItemId{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}
