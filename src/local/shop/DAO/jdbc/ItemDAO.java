package local.shop.DAO.jdbc;


import local.shop.DAO.DAO;
import local.shop.DAO.DBConnector;
import local.shop.model.deprecated.ShopItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements DAO<ShopItem> {

    @Override
    public List<ShopItem> select() {

         ArrayList<ShopItem> resultList = new ArrayList<>();
            try(PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM items")) {
                ResultSet result = ps.executeQuery();
                while (result.next()){
                    resultList.add(new ShopItem(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getFloat("price"),
                            result.getInt("count")
                    ));

                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        return resultList;
    }

    public  ShopItem selectById(ShopItem pattern){
        ShopItem selectedItem= new ShopItem();
        try(PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM items WHERE id=?")) {
            ps.setObject(1,pattern.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                selectedItem.setId(pattern.getId());
                selectedItem.setName(rs.getString("name"));
                selectedItem.setPrice(rs.getFloat("price"));
                selectedItem.setCountStored(rs.getInt("count"));
            }
            else selectedItem.setId(-1);
        }catch (SQLException e){
            System.out.println("Unable select by ID");
            e.printStackTrace();
        }
        return selectedItem;
    }
    public List<ShopItem> selectByName(ShopItem searchPattern) {
        ArrayList<ShopItem> items = new ArrayList<>();
        try(PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM items WHERE name LIKE ?")) {
            ps.setObject(1,'%'+searchPattern.getName()+'%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                items.add(new ShopItem(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("count")));
            }
        }catch (SQLException e){
            System.out.println("Select by name error");
            e.printStackTrace();
        }
        return items;
    }
    public  ShopItem insert(ShopItem item){
        try(PreparedStatement ps = DBConnector.getConnection().prepareStatement("INSERT INTO items(name,price,count) VALUES (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ){
            ps.setObject(1,item.getName());
            ps.setObject(2,item.getPrice());
            ps.setObject(3, item.getCountStored());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }
    public  boolean update(ShopItem item) {
        try(PreparedStatement ps = DBConnector.getConnection().prepareStatement("UPDATE items SET name=? , price=?, count=? WHERE id=?")){
            ps.setObject(1,item.getName());
            ps.setObject(2,item.getPrice());
            ps.setObject(3,item.getCountStored());
            ps.setObject(4,item.getId());
            int affected = ps.executeUpdate();
            if (affected>0) return true;
        }catch (SQLException e ){
            System.out.println("Update error");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(ShopItem item) {

    }
}
