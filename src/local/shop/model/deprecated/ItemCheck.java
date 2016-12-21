package local.shop.model.deprecated;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;


public class ItemCheck {

    private ObservableList<Entry> items = FXCollections.observableArrayList();

    private int id ;
    private int size;
    private float sum;

    public ItemCheck() {
    }

    public ItemCheck(int id, int size, float sum) {
        this.id = id;
        this.size = size;
        this.sum = sum;
    }

    public void addItem(ShopItem item, Integer count) {

        if (items.size() > 0) {
            for (Entry enrty : items) {
                if (item.equals(enrty.getItem())) {
                    enrty.addCount(count);
                    return;
                }
            }
            items.add(new Entry(item, count));
            size++;
        } else {
            items.add(new Entry(item, count));
            size++;
        }
    }
    public void addItem(ShopItem item,Integer count, int entryId){
        //заменить на другой конструктор ?
        addItem(item,count);
        items.get(size-1).setEntryId(entryId);
    }



    private void recalculateSum() {
        sum = 0.0f;
        for (Entry entry :
                items) {
            sum += (entry.getCount() * entry.getItem().getPrice());
        }
    }

    public ObservableList<Entry> getItems() {
        return items;
    }

    public float getSum() {
        recalculateSum();
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void delItem(int index) {
        items.remove(index);
        size--;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public SimpleIntegerProperty idProperty(){
        return new SimpleIntegerProperty(id);
    }
    public SimpleFloatProperty sumProperty() {
        return new SimpleFloatProperty(sum);
    }


    public class Entry {
        ShopItem item;
        int count;
        int entryId;
        Entry(ShopItem item, int count) {
            this.item = item;
            this.count = count;
        }
        Entry(ShopItem item , int count , int entryId){
            this.item = item;
            this.count = count;
            this.entryId = entryId;
        }


        public ShopItem getItem() {
            return item;
        }

        public int getCount() {
            return count;
        }

        public void addCount(int count) {
            this.count += count;
        }

        public void reduceCount(int count) {
            this.count -= count;
        }

        public SimpleIntegerProperty countProperty() {
            return new SimpleIntegerProperty(count);
        }

        public int getEntryId() {
            return entryId;
        }

        public void setEntryId(int entryId) {
            this.entryId = entryId;
        }

    }


}
