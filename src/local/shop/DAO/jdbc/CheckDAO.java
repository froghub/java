package local.shop.DAO.jdbc;

import local.shop.DAO.DAO;
import local.shop.DAO.DBConnector;
import local.shop.model.deprecated.ArchivedCheck;
import local.shop.model.deprecated.ItemCheck;
import local.shop.model.deprecated.ShopItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CheckDAO implements DAO<ItemCheck> {

    @Override
    public List<ItemCheck> select() {
        List<ItemCheck> checks = new ArrayList<>();
        try (PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM checks")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                checks.add(new ArchivedCheck(
                        rs.getInt("id"),
                        0,
                        rs.getFloat("total"),
                        rs.getString("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checks;
    }

    @Override
    public ItemCheck selectById(ItemCheck pattern) {

        ItemCheck check = new ArchivedCheck();
        try (PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM solditems WHERE checkid=?");
             PreparedStatement ps2 = DBConnector.getConnection().prepareStatement("SELECT * FROM items WHERE id=?")
        ) {
            ps.setInt(1, pattern.getId());
            ResultSet soldItemsSet = ps.executeQuery();

            while (soldItemsSet.next()) {
                int count = soldItemsSet.getInt("count");
                int entryId = soldItemsSet.getInt("id");
                ps2.setInt(1, soldItemsSet.getInt("itemid"));
                ResultSet itemSet = ps2.executeQuery();
                itemSet.next();
                //from sold items

                ShopItem item = new ShopItem(
                        soldItemsSet.getInt("itemid"),
                        itemSet.getString("name"),
                        soldItemsSet.getFloat("currentprice"), count);
                check.addItem(item, count,entryId);

               // check.getItems().get(check.getSize() - 1).setEntryId();

            }
            // ((ArchivedCheck) check).setCheckDate(soldItemsSet.getString("date"));

        } catch (SQLException e) {
            System.out.println("Check dao,select by ID, SQL Exception block");
            e.printStackTrace();
        }

        return check;
    }

    @Override
    public List<ItemCheck> selectByName(ItemCheck searchPattern) {
        return null;
    }


    @Override
    public ItemCheck insert(ItemCheck item) {
        int checkId = -1;
        String date = item instanceof ArchivedCheck ? ((ArchivedCheck) item).getCheckDate() : null;
        try (PreparedStatement ps = DBConnector.getConnection().prepareStatement(
                "INSERT INTO checks(total,date) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setObject(1, item.getSum());
            ps.setString(2,date);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            checkId = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (checkId < 0) return null;
        for (ItemCheck.Entry entry :
                item.getItems()) {
            try (PreparedStatement ps2 = DBConnector.getConnection().prepareStatement(
                    "INSERT INTO solditems(checkid,itemid,count,currentprice) VALUES(?,?,?,?)"
            )) {
                ps2.setInt(1, checkId);
                ps2.setObject(2, entry.getItem().getId());
                ps2.setObject(3, entry.getCount());
                ps2.setObject(4, entry.getItem().getPrice());
                ps2.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        item.setId(checkId);
        return item;
    }

    @Override
    public boolean update(ItemCheck item) {

        try (PreparedStatement ps = DBConnector.getConnection().prepareStatement("UPDATE checks SET total=? WHERE id=?");
             PreparedStatement ps2 = DBConnector.getConnection().prepareStatement("UPDATE solditems SET count=? WHERE id=?");
             PreparedStatement ps3 = DBConnector.getConnection().prepareStatement("DELETE FROM solditems WHERE id=?")) {
            ps.setFloat(1, item.getSum());
            ps.setInt(2, item.getId());
            ps.executeUpdate();
            for (ItemCheck.Entry entry : item.getItems()) {
                if (entry.getCount() > 0) {
                    ps2.setInt(1, entry.getCount());
                    ps2.setInt(2, entry.getEntryId());
                    ps2.executeUpdate();
                } else {
                    ps3.setInt(1, entry.getEntryId());
                    ps3.executeUpdate();
                    //entry.setEntryId(0);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CheckDAO update error");
            return false;
        }
        return true;
    }

    @Override
    public void delete(ItemCheck item) {
        try(PreparedStatement ps = DBConnector.getConnection().prepareStatement("DELETE FROM checks WHERE id=?");
        PreparedStatement ps2 = DBConnector.getConnection().prepareStatement("DELETE FROM solditems WHERE id=?")){
            ps.setInt(1,item.getId());
            ps.executeUpdate();
            for (ItemCheck.Entry entry : item.getItems()){
                ps2.setInt(1,entry.getEntryId());
                ps2.executeUpdate();
            }
        }catch (SQLException e ){
            System.out.println("CheckDAO , delete error");
            e.printStackTrace();
        }
    }
}

