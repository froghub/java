package local.shop.model.deprecated;


public class SoldItem extends ShopItem {

    int soldCount;
    float soldPrice;
    String soldDate;

    public SoldItem() {
    }

    public SoldItem(int id, String name, int soldCount, float soldPrice, String soldDate) {
        super(id, name, 0, 0);
        this.soldCount = soldCount;
        this.soldPrice = soldPrice;
        this.soldDate = soldDate;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public float getSoldPrice() {
        return soldPrice;
    }

    public String getSoldDate() {
        return soldDate;
    }

}
