package local.shop.controller;


import local.shop.DAO.DBConnector;
import local.shop.exceptions.NotEnoughItemsOnStore;
import local.shop.model.deprecated.ItemCheck;
import local.shop.model.entity.ChecksEntity;
import local.shop.model.entity.ProductsEntity;
import local.shop.model.entity.SolditemsEntity;

public class CheckUtil {


    public static boolean processCheck(ChecksEntity check) throws NotEnoughItemsOnStore {

       for (SolditemsEntity checkEntry : check.getItems()) {
            ProductsEntity checkable = DBConnector.getProductDAO().selectById(checkEntry.getProduct());
            int requiredCount = checkEntry.getCount();
            int storedCount = checkable.getCount();
            if (requiredCount > storedCount) throw new NotEnoughItemsOnStore(checkable);
            int newStoredCount = storedCount - requiredCount;
            checkEntry.getProduct().setCount((short)newStoredCount);
        }

        /// ВНЕСЕНИЕ ЧЕКА В БД
         check = DBConnector.getCheckDAO().insert(check);

           if(check.getId()!=0) return true;
            return false;
    }

    public static ChecksEntity loadCheckData(ChecksEntity check) {
        return DBConnector.getCheckDAO().selectById(check);
    }

    public static void reclamationUpdate(ChecksEntity check) {


        /*if(DBConnector.getCheckDAO().update(check)){
            for(ItemCheck.Entry entry : check.getItems()){
                if (entry.getCount()!=entry.getItem().getCountStored()){
                    //Неверное количество. Необходимо актуальное из БД
                    //Прибавить к количеству из бд
                    int currentStoredCount = DBConnector.getItemDAO().selectById(entry.getItem()).getCountStored();
                    int newCountStored = currentStoredCount+(entry.getItem().getCountStored()-entry.getCount());
                    entry.getItem().setCountStored(newCountStored);
                    DBConnector.getItemDAO().update(entry.getItem());
                }
            }*/
        }
       /* else {
            DBConnector.getCheckDAO().delete(check);
            DBConnector.getCheckDAO().insert(check);
        } */
    }


}
