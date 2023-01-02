package application;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entities.Commodity;
import model.entities.User;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Dao<User> useDao = DaoFactory.createUserDao();
        User user1 = useDao.findById("joao345");

        System.out.println(user1);
    }
}