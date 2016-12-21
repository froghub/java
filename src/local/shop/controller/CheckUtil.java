package local.shop.controller;


import local.shop.DAO.DBConnector;
import local.shop.exceptions.NotEnoughItemsOnStore;
import local.shop.model.deprecated.ItemCheck;
import local.shop.model.entity.ChecksEntity;

public class CheckUtil {


    public static boolean processCheck(ChecksEntity check) throws NotEnoughItemsOnStore {

      /*  for (ItemCheck.Entry entry : check.getItems()) {
            ShopItem checkable = DBConnector.getItemDAO().selectById(entry.getItem());
            int requiredCount = entry.getCount();
            int storedCount = checkable.getCountStored();
            if (requiredCount > storedCount) throw new NotEnoughItemsOnStore(checkable);
            int newStoredCount = storedCount - requiredCount;
            entry.getItem().setCountStored(newStoredCount);
        }

        /// ВНЕСЕНИЕ ЧЕКА В БД
        if (DBConnector.getCheckDAO().insert(check) != null) {
            for (ItemCheck.Entry entry : check.getItems()) {
                DBConnector.getItemDAO().update(entry.getItem());
            }

            return true;
        }*/

        return false;
    }

    public static ItemCheck loadCheckData(ItemCheck check) {
        return DBConnector.getCheckDAO().selectById(check);
    }

    public static void reclamationUpdate(ItemCheck check) {
        if(DBConnector.getCheckDAO().update(check)){
            for(ItemCheck.Entry entry : check.getItems()){
                if (entry.getCount()!=entry.getItem().getCountStored()){
                    //Неверное количество. Необходимо актуальное из БД
                    //Прибавить к количеству из бд
                    int currentStoredCount = DBConnector.getItemDAO().selectById(entry.getItem()).getCountStored();
                    int newCountStored = currentStoredCount+(entry.getItem().getCountStored()-entry.getCount());
                    entry.getItem().setCountStored(newCountStored);
                    DBConnector.getItemDAO().update(entry.getItem());
                }
            }
        }
        else {
            DBConnector.getCheckDAO().delete(check);
            DBConnector.getCheckDAO().insert(check);
        }
    }


}
