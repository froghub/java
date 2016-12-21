package local.shop.DAO;


import java.util.List;

public interface DAO<T> {
    //TODO заменить параметр поиска по id на int
    // либо заменить весь поиск на 1 метод,в котором будут поочередно проверяться все поля
    // и поиск будет по недефолтному значению

    T selectById(T pattern);
    List<T> select();
    List<T> selectByName(T searchPattern);
    T insert(T item);
    boolean update(T item);
    void delete(T item);
}
