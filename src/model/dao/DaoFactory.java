package model.dao;

import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

    public static Dao<?> createUserDao() {
        return new UserDaoJDBC();
    }
}
