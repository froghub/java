package local.shop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by evilbox on 20.12.16.
 */
@Entity
@Table(name = "waybillitems", schema = "shop", catalog = "")
public class WaybillitemsEntity {
    private int id;
    private short waybillId;
    private int itemId;
    private short count;
    private BigDecimal price;
    private byte shipperId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "waybill_id", nullable = false)
    public short getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(short waybillId) {
        this.waybillId = waybillId;
    }

    @Basic
    @Column(name = "item_id", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "shipper_id", nullable = false)
    public byte getShipperId() {
        return shipperId;
    }

    public void setShipperId(byte shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaybillitemsEntity that = (WaybillitemsEntity) o;

        if (id != that.id) return false;
        if (waybillId != that.waybillId) return false;
        if (itemId != that.itemId) return false;
        if (count != that.count) return false;
        if (shipperId != that.shipperId) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) waybillId;
        result = 31 * result + itemId;
        result = 31 * result + (int) count;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) shipperId;
        return result;
    }
}
