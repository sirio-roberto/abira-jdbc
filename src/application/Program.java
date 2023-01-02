package application;


import db.DB;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    // ('sirio123', 'Sirio', 'ThirdParty', 'sirio@gmail.com', '2015-12-20 10:01:00', '2015-12-20 10:01:00')
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO user "
                    + "(Id, Name, UserType, Email, TimeCreated, TimeUpdated) "
                    + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "sirio4561");
            st.setString(2, "Sirius JJ");
            st.setString(3, "ThirdParty");
            st.setString(4, "sirio@gmail.com");
            st.setDate(5, new Date(sdf.parse("12/12/2022").getTime()));
            st.setDate(6, new Date(sdf.parse("13/08/2022").getTime()));

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    String id = rs.getString(1);
                    System.out.println("Id = " + id);
                }
            } else {
                System.out.println("No rows affected");
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {

        }
    }
}