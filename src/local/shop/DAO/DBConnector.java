package local.shop.DAO;

import local.shop.DAO.ProductDAO;
import local.shop.DAO.jdbc.CheckDAO;
import local.shop.DAO.DAO;
import local.shop.DAO.jdbc.ItemDAO;
import local.shop.model.deprecated.ItemCheck;
import local.shop.model.deprecated.ShopItem;
import local.shop.model.entity.ProductsEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





public class DBConnector {
    private  static  Connection connection ;
    private static SessionFactory sessionFactory;
    private static String URL = "jdbc:mysql://localhost/shop?user=root&password=mysqlroot&characterEncoding=utf8&useSSL=false";

    private DBConnector() {
    }

    public static Connection getConnection() {
        try {
            if (connection==null||connection.isClosed()){
                connection = DriverManager.getConnection(URL);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Unable to get MySQL connection");
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory==null) sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
    public static DAO<ShopItem> getItemDAO() {
         return new ItemDAO();
     }
    public static DAO<ItemCheck> getCheckDAO() {
        return new CheckDAO();
    }
    public static DAO<ProductsEntity> getProductDAO() { return new ProductDAO(); }
}
