package model.entities;

import java.util.List;

public interface Dao<T> {

    void insert(T obj);
    void update(T obj);
    void deleteById(String id);
    T findById(String id);
    List<T> findAll();
}
