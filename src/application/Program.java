package application;

import model.entities.Commodity;
import model.entities.User;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Commodity commodity = new Commodity("All", "All", "custom");
        System.out.println(commodity);

        User user1 = new User("sirios1", "Sirio", "Enterprise", "sirio@gmail.com", new Date(), commodity);
        System.out.println(user1);
    }
}