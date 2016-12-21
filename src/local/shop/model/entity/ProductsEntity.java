package local.shop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "products", schema = "shop", catalog = "")
public class ProductsEntity {
    private int id;
    private String name;
    private float price;
    private short count;
    private boolean archived;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public short getCount() {
        return count;
    }
    public void setCount(short count) {
        this.count = count;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Column(name = "archived" , nullable = false)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price !=that.price) return false;
         //TODO equals 4 archived

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) price ;
        result = 31 * result + (int) count;
        return result;
    }
}
