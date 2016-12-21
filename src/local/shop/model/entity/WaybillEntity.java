package local.shop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by evilbox on 20.12.16.
 */
@Entity
@Table(name = "waybill", schema = "shop", catalog = "")
public class WaybillEntity {
    private short id;
    private BigDecimal waybillSum;
    private Timestamp date;
    private byte isReceived;
    private Timestamp receiveDate;

    @Id
    @Column(name = "id", nullable = false)
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "waybill_sum", nullable = false, precision = 2)
    public BigDecimal getWaybillSum() {
        return waybillSum;
    }

    public void setWaybillSum(BigDecimal waybillSum) {
        this.waybillSum = waybillSum;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "is_received", nullable = false)
    public byte getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(byte isReceived) {
        this.isReceived = isReceived;
    }

    @Basic
    @Column(name = "receive_date", nullable = false)
    public Timestamp getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Timestamp receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaybillEntity that = (WaybillEntity) o;

        if (id != that.id) return false;
        if (isReceived != that.isReceived) return false;
        if (waybillSum != null ? !waybillSum.equals(that.waybillSum) : that.waybillSum != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (receiveDate != null ? !receiveDate.equals(that.receiveDate) : that.receiveDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (waybillSum != null ? waybillSum.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) isReceived;
        result = 31 * result + (receiveDate != null ? receiveDate.hashCode() : 0);
        return result;
    }
}
