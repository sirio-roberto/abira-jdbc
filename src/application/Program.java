package application;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.Commodity;
import model.entities.User;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        UserDao userDao = DaoFactory.createUserDao();

        System.out.println("--- TEST1 : user findById ---");
        User user1 = userDao.findById("joao345");
        System.out.println(user1);
        System.out.println();

        System.out.println("--- TEST2 : user findByCommodity ---");
        Commodity commodity1 = new Commodity("F1", null, "custom");
        List<User> users2 = userDao.findByCommodity(commodity1);
        for (User u : users2) {
            System.out.println(u);
        }
        System.out.println();
    }
}