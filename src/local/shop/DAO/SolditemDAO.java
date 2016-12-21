package local.shop.DAO;

import local.shop.model.entity.SolditemsEntity;

import java.util.List;

/**
 * Created by evilbox on 20.12.16.
 */
public class SolditemDAO implements DAO<SolditemsEntity> {
    @Override
    public SolditemsEntity selectById(SolditemsEntity pattern) {
        return null;
    }

    @Override
    public List<SolditemsEntity> select() {
        return null;
    }

    @Override
    public List<SolditemsEntity> selectByName(SolditemsEntity searchPattern) {
        return null;
    }

    @Override
    public SolditemsEntity insert(SolditemsEntity item) {
        return null;
    }

    @Override
    public boolean update(SolditemsEntity item) {
        return false;
    }

    @Override
    public void delete(SolditemsEntity item) {

    }
}
