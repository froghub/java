package local.shop.model.entity;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "checks", schema = "shop", catalog = "")
public class ChecksEntity {

//    public void addSolditemsEntity(SolditemsEntity entity){
//        items.add(entity);
//        entity.setCheck(this);
//    }
//    public void removeSolditemsEntity(SolditemsEntity entity){
//        items.remove(entity);
//        entity.setCheck(null);
//    }
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @OneToMany
    private List<SolditemsEntity> items = new ArrayList<>();
    public List<SolditemsEntity> getItems() {
        return items;
    }
    public void setItems(List<SolditemsEntity> items) {
        this.items = items;
    }


    @Basic
    @Column(name = "total", nullable = false, precision = 2)
    private float total;
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
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

        ChecksEntity that = (ChecksEntity) o;

        if (id != that.id) return false;
        if (total != that.total) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }


    public ObservableList<SolditemsEntity> toObservableList(){
        return FXCollections.observableArrayList(items);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int)total;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public void remove(int idx){
        items.remove(idx);
    }
    public void addItem(ProductsEntity item, int count){
        // Create Sold item entity
        SolditemsEntity addable = new SolditemsEntity();
        addable.setCount((short)count);
        addable.setProduct(item);
        items.add(addable);
        total+=count*item.getPrice();
    }
}
