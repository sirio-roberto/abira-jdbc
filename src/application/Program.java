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

        System.out.println("--- TEST3 : user findAll ---");
        List<User> users3 = userDao.findAll();
        for (User u : users3) {
            System.out.println(u);
        }
        System.out.println();

        System.out.println("--- TEST4 : user insert ---");
        User user4 = new User("venz12345", "Venz", "Supplier", "venz@gmail.com", new Date(), new Commodity("F3", "Seafood", "custom"));
        userDao.insert(user4);
        System.out.println("User inserted = " + user4.getId());
        System.out.println();

        System.out.println("--- TEST5 : user update ---");
        user1.setName("Jatão da Silva");
        userDao.update(user1);
        System.out.println("User updated!");
        System.out.println();

        System.out.println("--- TEST6 : user delete ---");
        String id = "venz12345";
        userDao.deleteById(id);
        System.out.println("User deleted!");
        System.out.println();
    }
}