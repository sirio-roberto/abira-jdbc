package application;

import model.entities.Commodity;

public class Program {
    public static void main(String[] args) {
        Commodity commodity = new Commodity("All", "All", "custom");
        System.out.println(commodity);
    }
}