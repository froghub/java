package local.shop.controller;


import local.shop.DAO.DBConnector;
import local.shop.model.entity.ProductsEntity;

import java.util.Iterator;
import java.util.List;

public class ItemUtil {


    public static List<ProductsEntity> searchByName(String name) {
        ProductsEntity item = new ProductsEntity();
        item.setName(name);
        return   DBConnector.getProductDAO().selectByName(item);
    }
    public static List<ProductsEntity> searchByNameExcludeZeroCount(String name) {
        ProductsEntity item = new ProductsEntity();
        item.setName(name);
        List<ProductsEntity> result = DBConnector.getProductDAO().selectByName(item);
        Iterator<ProductsEntity> iterator = result.iterator();
        while (iterator.hasNext()){
            ProductsEntity temp = iterator.next();
            if (temp.getCount()==0) iterator.remove();
        }
        return   result;
    }

}
