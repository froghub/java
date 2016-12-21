package local.shop.DAO;

import local.shop.model.entity.ProductsEntity;
import org.hibernate.Session;

import java.util.List;


public class ProductDAO implements DAO<ProductsEntity> {
    @Override
    public ProductsEntity selectById(ProductsEntity pattern) {
        Session session = DBConnector.getSessionFactory().openSession();
        return session.get(ProductsEntity.class,pattern.getId());
    }

    @Override
    public List<ProductsEntity> select() {
        List<ProductsEntity> list;
       Session session = DBConnector.getSessionFactory().openSession();
        list = session.createQuery("select p from ProductsEntity p",ProductsEntity.class).list();
        return list;
    }

    @Override
    public List<ProductsEntity> selectByName(ProductsEntity searchPattern) {
        List<ProductsEntity> list;
        Session session = DBConnector.getSessionFactory().openSession();

        list = session.createQuery("select p from ProductsEntity p where p.name like :name",ProductsEntity.class)
                .setParameter("name","%"+searchPattern.getName()+"%").list();

        return list;
    }

    @Override
    public ProductsEntity insert(ProductsEntity item) {
        Session session = DBConnector.getSessionFactory().openSession();
        session.save(item);
        return  item;
    }

    @Override
    public boolean update(ProductsEntity item) {
        Session session = DBConnector.getSessionFactory().openSession();
        session.update(item);

        return true;
    }

    @Override
    public void delete(ProductsEntity item) {

    }
}
