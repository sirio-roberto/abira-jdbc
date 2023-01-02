package model.dao;

import db.DB;
import model.dao.impl.UserDaoJDBC;
import model.entities.User;

public class DaoFactory {

    public static Dao<User> createUserDao() {
        return new UserDaoJDBC(DB.getConnection());
    }
}
