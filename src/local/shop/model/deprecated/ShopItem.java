package local.shop.model.deprecated;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ShopItem    {


    private int id;
    private String name;
    private float price;
    private int countStored;


    public ShopItem(int id, String name, float price, int countStored) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.countStored = countStored;
    }


    public ShopItem() {
    }

    public int getId() {
        return id;
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SimpleStringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public SimpleFloatProperty priceProperty() {
        return new SimpleFloatProperty(price);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCountStored() {
        return countStored;
    }

    public SimpleIntegerProperty countStoredProperty() {
        return new SimpleIntegerProperty(countStored);
    }

    public void setCountStored(int countStored) {
        this.countStored = countStored;
    }



    ///TODO : Пересмотреть метод equals. Возможно достаточно будет сравнения по ID
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ShopItem) {
            ShopItem o = (ShopItem) obj;
            if (o.getId() == id && o.getName().equals(name) && o.getPrice() == price) {
                return true;
            }
        }
        return false;
    }


}
