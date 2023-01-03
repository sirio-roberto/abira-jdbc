package model.dao;

import model.dao.Dao;
import model.entities.Commodity;
import model.entities.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> findByCommodity(Commodity commodity);
}
