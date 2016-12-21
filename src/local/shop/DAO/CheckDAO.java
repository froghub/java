package local.shop.DAO;

import local.shop.model.entity.ChecksEntity;
import org.hibernate.Session;

import java.util.List;

public class CheckDAO implements DAO<ChecksEntity> {
    @Override
    public ChecksEntity selectById(ChecksEntity pattern) {

        Session session = DBConnector.getSessionFactory().openSession();
        return  (ChecksEntity)session.get(ChecksEntity.class,pattern.getId());
    }

    @Override
    public List<ChecksEntity> select() {
        return null;
    }

    @Override
    public List<ChecksEntity> selectByName(ChecksEntity searchPattern) {
        //Session session = DBConnector.getSessionFactory().openSession();


        return null;
    }

    @Override
    public ChecksEntity insert(ChecksEntity item) {
        Session session = DBConnector.getSessionFactory().openSession();
        item.setId((int)session.save(item));

        return item;
    }

    @Override
    public boolean update(ChecksEntity item) {
        return false;
    }

    @Override
    public void delete(ChecksEntity item) {

    }
}
