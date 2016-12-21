package local.shop.exceptions;


import local.shop.model.entity.ProductsEntity;

public class NotEnoughItemsOnStore extends Throwable {
    private String err;
    ProductsEntity item;
    public NotEnoughItemsOnStore(String err){
        this.err = err;
    }
    public NotEnoughItemsOnStore(ProductsEntity item){
        this.item = item;
    }
    public String getErrMsg(){
        if (err==null){
            err="Недостаточно товаров на складе: "+item.getName()+" есть: "+item.getCount();
        }
        return err;
    }
}
