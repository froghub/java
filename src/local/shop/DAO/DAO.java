package local.shop.DAO;


import java.util.List;

public interface DAO<T> {
    T selectById(T pattern);
    List<T> select();
    List<T> selectByName(T searchPattern);
    T insert(T item);
    boolean update(T item);
    void delete(T item);
}
