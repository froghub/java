package local.shop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "solditems", schema = "shop", catalog = "")
public class SolditemsEntity {

   // private int checkid;







    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*@Basic
    @Column(name = "checkid", nullable = false)
    public int getCheckid() {
        return checkid;
    }

    public void setCheckid(int checkid) {
        this.checkid = checkid;
    }*/

    @ManyToOne
    @JoinColumn(name = "checkid")
    private ChecksEntity check;

    public ChecksEntity getCheck() {
        return check;
    }
    public void setCheck(ChecksEntity check){
        this.check = check;
    }

    @OneToOne
    @JoinColumn(name = "itemid")
    private ProductsEntity product;
    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }


    @Basic
    @Column(name = "count", nullable = false)
    private short count;
    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }


    @Basic
    @Column(name = "date", nullable = false)
    private Timestamp date;
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolditemsEntity that = (SolditemsEntity) o;

        if (id != that.id) return false;
        //if (checkid != that.checkid) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (count != that.count) return false;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        //result = 31 * result + checkid;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (int) count;

        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
