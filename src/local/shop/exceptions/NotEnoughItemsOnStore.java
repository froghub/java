package local.shop.exceptions;


import local.shop.model.deprecated.ShopItem;

public class NotEnoughItemsOnStore extends Throwable {
    private String err;
    ShopItem item;
    public NotEnoughItemsOnStore(String err){
        this.err = err;
    }
    public NotEnoughItemsOnStore(ShopItem item){
        this.item = item;
    }
    public String getErrMsg(){
        if (err==null){
            err="Недостаточно товаров на складе: "+item.getName()+" есть: "+item.getCountStored();
        }
        return err;
    }
}
